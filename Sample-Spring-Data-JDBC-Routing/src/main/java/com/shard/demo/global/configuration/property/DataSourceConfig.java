package com.shard.demo.global.configuration.property;

public record DataSourceConfig (
    String host,
    String name,
    String url,
    String username,
    String password,
    String driverClassName
) { }
