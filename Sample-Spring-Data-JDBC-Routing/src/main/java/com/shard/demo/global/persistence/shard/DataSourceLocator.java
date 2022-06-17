package com.shard.demo.global.persistence.shard;

import com.shard.demo.global.persistence.shard.metadata.ShardMetaInfo;

public interface DataSourceLocator {
    String getTargetShard(ShardMetaInfo key);
}
