package com.cloud.gateway.gateway.resource.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public record ResourceServiceProxy(
        @Qualifier("resourceWebClient") WebClient webClient
) {
    public Mono<String> getMessage() {
        log.info("Call Resource Proxy");

        return webClient.get()
                .uri("/greeting")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(RuntimeException::new))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(RuntimeException::new))
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10));
    }
}
