package com.shard.demo.global.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@ConstructorBinding
@ConfigurationProperties(prefix = "shard-policy")
public record ShardPolicyProperty (
        List<ShardConfig> configs
) { }
