package com.shard.demo.global.persistence.shard;

public interface DataSourceLocator {
    String getTargetShard(ShardMetaInfo key);
}
