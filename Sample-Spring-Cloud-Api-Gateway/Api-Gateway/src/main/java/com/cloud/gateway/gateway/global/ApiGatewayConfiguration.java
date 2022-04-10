package com.cloud.gateway.gateway.global;

import com.cloud.gateway.gateway.authorization.handler.SampleAuthorizationHandler;
import com.cloud.gateway.gateway.authorization.property.AuthorizationProperty;
import com.cloud.gateway.gateway.global.property.ApiGatewayProperty;
import com.cloud.gateway.gateway.resource.handler.SampleResourceHandler;
import com.cloud.gateway.gateway.resource.property.ResourceProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApiGatewayConfiguration {

    private final AuthorizationProperty authorizationProperty;
    private final ApiGatewayProperty apiGatewayProperty;
    private final ResourceProperty resourceProperty;

    @Bean
    protected RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec
                        .path("/users/**")
                        .filters(filterSpec -> filterSpec
                                .requestRateLimiter(limiter -> limiter
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(keyResolver())
                                ))
                        .uri(authorizationProperty.apiUrl()))

                .route(predicateSpec -> predicateSpec
                        .path("/resources/**")
                        .filters(filterSpec -> filterSpec
                                .requestRateLimiter(limiter -> limiter
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(keyResolver())
                                ))
                        .uri(resourceProperty.apiUrl()))
                .build();
    }

    @Bean
    protected RouterFunction<ServerResponse> authorizationHandlerRouting(
            SampleAuthorizationHandler authorizationHandler,
            SampleResourceHandler resourceHandler
    ) {
        return RouterFunctions
                .route(RequestPredicates.GET("/user-proxy"), authorizationHandler::greeting)
                .andRoute(RequestPredicates.GET("/resource-proxy"), resourceHandler::greeting);
    }

    /*  redis-cli
        1) "request_rate_limiter.{key::1}.tokens"
        2) "request_rate_limiter.{key::1}.timestamp"
    */
    @Bean
    protected KeyResolver keyResolver() {
        return exchange -> Mono.just(apiGatewayProperty.rateKey());
    }

    @Bean(name = "authorizationWebClient")
    protected WebClient authorizationWebClient() {
        return WebClient.builder()
                .baseUrl(authorizationProperty.apiUrl())
                .build();
    }

    @Bean(name = "resourceWebClient")
    protected WebClient resourceWebClient() {
        return WebClient.builder()
                .baseUrl(resourceProperty.apiUrl())
                .build();
    }

    /*
    Spring boot 2.2+ 이후 -> management.endpoints.web.exposure.include = httptrace 활성화 방법
     */
    @Bean
    protected HttpTraceRepository httpTraceRepository() {
        InMemoryHttpTraceRepository httpTraceRepository = new InMemoryHttpTraceRepository();
        httpTraceRepository.setCapacity(200);
        return httpTraceRepository;
    }

    @Bean
    protected RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(5, 10);
    }
}
