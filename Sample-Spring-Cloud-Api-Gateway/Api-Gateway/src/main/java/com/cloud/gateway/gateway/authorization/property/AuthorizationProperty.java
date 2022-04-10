package com.cloud.gateway.gateway.authorization.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "gateway.proxy.authorization")
public record AuthorizationProperty(
        String apiUrl
) {
}
