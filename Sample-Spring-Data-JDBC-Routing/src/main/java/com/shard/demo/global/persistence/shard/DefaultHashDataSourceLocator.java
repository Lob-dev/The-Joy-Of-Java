package com.shard.demo.global.persistence.shard;

import com.shard.demo.global.configuration.property.DataSourceConfig;
import com.shard.demo.global.configuration.property.ShardConfig;
import com.shard.demo.global.persistence.shard.metadata.ShardMetaInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class DefaultHashDataSourceLocator implements DataSourceLocator {

    private final Map<String, List<String>> routeTargets;

    public DefaultHashDataSourceLocator(
            List<DataSourceConfig> dataSourceConfigs,
            List<ShardConfig> shardConfigs
    ) {
        this.routeTargets = initRoutePathsByTarget(dataSourceConfigs, shardConfigs);
    }

    private HashMap<String, List<String>> initRoutePathsByTarget(
            List<DataSourceConfig> dataSourceConfigs,
            List<ShardConfig> shardConfigs
    ) {
        final ArrayList<String> routePaths = dataSourceConfigs.stream()
                .map(DataSourceConfig::name)
                .map(name -> name.split("_")[0])
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));

        final HashMap<String, List<String>> routeTargets = new HashMap<>();
        shardConfigs.forEach(shardConfig -> {
            routeTargets.put(shardConfig.target(), routePaths);
        });
        return routeTargets;
    }

    @Override
    public String getTargetShard(ShardMetaInfo shardMetaInfo) {
        final List<String> routePaths = routeTargets.get(shardMetaInfo.target());
        if (CollectionUtils.isEmpty(routePaths)) {
            return DefaultShardTarget.PRIMARY.name();
        }

        final int index = (int) shardMetaInfo.value() % routePaths.size();
        return routePaths.get(index);
    }
}
