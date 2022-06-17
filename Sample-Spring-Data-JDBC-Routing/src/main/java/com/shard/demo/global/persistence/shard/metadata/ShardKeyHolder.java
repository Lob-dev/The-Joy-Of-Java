package com.shard.demo.global.persistence.shard.metadata;

public class ShardKeyHolder {
    private static final ThreadLocal<ShardMetaInfo> holder = new ThreadLocal<>();

    public static ShardMetaInfo getShardKey() {
        return holder.get();
    }

    public static void setShardKey(ShardMetaInfo shardMetaInfo) {
        holder.set(shardMetaInfo);
    }
}
