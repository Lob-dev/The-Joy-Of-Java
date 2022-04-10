package com.shard.demo.global.persistence.shard;

public record ShardMetaInfo(
        String target,
        long value
) { }
