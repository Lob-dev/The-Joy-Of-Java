package com.cloud.gateway.gateway.global.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "gateway")
public record ApiGatewayProperty(
        String rateKey
) {
}
