package com.cloud.gateway.gateway.resource.handler;

import com.cloud.gateway.gateway.resource.proxy.ResourceServiceProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
public record SampleResourceHandler(
        ResourceServiceProxy serviceProxy
) {
    public Mono<ServerResponse> greeting(ServerRequest request) {
        log.info("request = {}", request);

        final Mono<String> message = serviceProxy.getMessage();

        return message.flatMap(response -> ServerResponse.ok()
                .bodyValue(response)
                .onErrorResume(RuntimeException.class, exception -> ServerResponse.status(INTERNAL_SERVER_ERROR).build()
                ));
    }
}
