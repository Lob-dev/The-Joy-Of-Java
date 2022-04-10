package com.shard.demo.global.persistence.route;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataSourceRouteKey {

    READ_ONLY("read_only"),
    READ_WRITE("read_write");

    private final String value;
}
