package com.shard.demo.global.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@ConstructorBinding
@ConfigurationProperties(prefix = "data-sources")
public record DataSourceProperty (
        List<DataSourceConfig> configs
) { }
