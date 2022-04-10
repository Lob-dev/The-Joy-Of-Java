package com.cloud.gateway.gateway.global.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global Request logging. uri = {}", exchange.getRequest().getPath());
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    log.info("Global Response logging. http status = {}", exchange.getResponse().getStatusCode());
                }));
    }

    /**
    *   GatewayFilter 등록시 우선 모두 필터 체인에 추가하고 이후에 Ordered Interface의 getOrder() 반환 값을 이용해 정렬한다.
    **/
    @Override
    public int getOrder() {
        return -1;
    }
}
