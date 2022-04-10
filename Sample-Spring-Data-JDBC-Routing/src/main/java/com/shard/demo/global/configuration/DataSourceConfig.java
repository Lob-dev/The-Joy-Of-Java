package com.shard.demo.global.configuration;

public record DataSourceConfig (
    String name,
    String url,
    String username,
    String password,
    String driverClassName
) { }
