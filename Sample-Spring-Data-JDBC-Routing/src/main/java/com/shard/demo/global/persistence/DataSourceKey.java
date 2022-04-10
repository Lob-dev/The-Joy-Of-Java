package com.shard.demo.global.persistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataSourceKey {

    PRIMARY_ORIGIN("primary-origin"),
    PRIMARY_REPLICA("primary-replica"),
    SECONDARY_ORIGIN("secondary-origin"),
    SECONDARY_REPLICA("secondary-replica");

    private final String value;
}
