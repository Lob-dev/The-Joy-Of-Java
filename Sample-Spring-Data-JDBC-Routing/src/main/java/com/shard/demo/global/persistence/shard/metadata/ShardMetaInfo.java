package com.shard.demo.global.persistence.shard.metadata;

public record ShardMetaInfo(
        String target,
        long value
) { }
