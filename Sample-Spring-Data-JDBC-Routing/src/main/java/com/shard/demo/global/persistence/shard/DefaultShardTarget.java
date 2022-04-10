package com.shard.demo.global.persistence.shard;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DefaultShardTarget {

    PRIMARY("primary");

    private final String value;
}
