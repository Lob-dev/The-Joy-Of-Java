package com.cloud.gateway.gateway.resource.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "gateway.proxy.resource")
public record ResourceProperty(
        String apiUrl
) {
}
