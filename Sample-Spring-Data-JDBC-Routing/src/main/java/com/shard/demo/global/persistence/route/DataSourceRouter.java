package com.shard.demo.global.persistence.route;

import com.shard.demo.global.persistence.shard.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

import static com.shard.demo.global.utils.AssertionUtil.isNotNull;

@Slf4j
public class DataSourceRouter extends AbstractRoutingDataSource {

    private final DataSourceLocator dataSourceLocator;

    public DataSourceRouter(Map<Object, Object> dataSources, DataSourceLocator dataSourceLocator) {
        super.setTargetDataSources(dataSources);
        super.setDefaultTargetDataSource(dataSources.entrySet().iterator().next().getValue());
        this.dataSourceLocator = dataSourceLocator;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        // TODO: 2022-04-09 Shard
        final ShardMetaInfo shardKey = ShardKeyHolder.getShardKey();
        final String shardOption = isNotNull(shardKey) ? dataSourceLocator.getTargetShard(shardKey) :
                DefaultShardTarget.PRIMARY.getValue();

        // TODO: 2022-04-09 Replication query Off
        final boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        if (actualTransactionActive) {
            final boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            final String readOption = isReadOnly ?
                    DataSourceRouteKey.READ_ONLY.getValue() :
                    DataSourceRouteKey.READ_WRITE.getValue();
            final String path = String.format("%s_%s", shardOption, readOption);
            log.info("executed path = {}", path);
            return path;
        }
        final String path = String.format("%s_%s", shardOption, DataSourceRouteKey.READ_WRITE.getValue());
        log.info("executed path = {}", path);
        return path;
    }
}