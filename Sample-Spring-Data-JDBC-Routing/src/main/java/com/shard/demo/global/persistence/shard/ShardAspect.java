package com.shard.demo.global.persistence.shard;

import com.shard.demo.global.persistence.shard.metadata.ShardKeyHolder;
import com.shard.demo.global.persistence.shard.metadata.ShardMetaInfo;
import com.shard.demo.global.persistence.shard.metadata.Sharding;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class ShardAspect {

    @Pointcut(value = "@annotation(sharding)")
    public void ShardingPointcut(Sharding sharding) {
    }

    @Around(
            value = "ShardingPointcut(sharding) && args(entity)",
            argNames = "joinPoint, sharding, entity"
    )
    public Object process(ProceedingJoinPoint joinPoint, Sharding sharding, Object entity) throws Throwable {
        final Field declaredField = entity.getClass().getDeclaredField(sharding.key().split("\\.")[1]);
        declaredField.setAccessible(true);
        final Long accountId = (Long) ReflectionUtils.getField(declaredField, entity);

        ShardKeyHolder.setShardKey(new ShardMetaInfo(sharding.target(), accountId == null ? 0 : accountId));
        try {
            return joinPoint.proceed();
        } finally {
            declaredField.setAccessible(false);
            ShardKeyHolder.setShardKey(null);
        }
    }
}
