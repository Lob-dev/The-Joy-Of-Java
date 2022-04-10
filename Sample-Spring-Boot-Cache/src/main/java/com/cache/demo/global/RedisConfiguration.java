package com.cache.demo.global;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.time.Duration;

import static com.cache.demo.global.RedisConfiguration.CacheKey.FIVE;
import static com.cache.demo.global.RedisConfiguration.CacheKey.ONE;
import static com.cache.demo.global.RedisConfiguration.CacheKey.SAMPLE_KEY;
import static com.cache.demo.global.RedisConfiguration.CacheKey.TEMP_KEY;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class CacheKey {
		public static long ONE = 1L;
		public static long FIVE = 5L;
		public static final String SAMPLE_KEY = "SAMPLE";
		// sample
		public static final String TEMP_KEY = "TEMP";
	}

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private Integer port;

	@Bean
	public RedisConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory(host, port);
	}

	/*
	desc  : Custom configuration 등록
	refer : https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#redis:support:cache-abstraction
	*/
	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return defaultCacheConfig()
				.disableCachingNullValues()
				.serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}

	/*
	desc  : Custom configuration 에 대한 세부적인 설정 값을 조정 가능한 Customizer 등록
	refer : https://github.com/eugenp/tutorials/blob/master/spring-caching-2/src/main/java/com/baeldung/caching/redis/CacheConfig.java
	*/
	@Bean
	public RedisCacheManagerBuilderCustomizer cacheManagerBuilderCustomizer() {
		return (RedisCacheManager.RedisCacheManagerBuilder builder) -> builder
				.withCacheConfiguration(SAMPLE_KEY, defaultCacheConfig().entryTtl(Duration.ofMinutes(ONE)))
				.withCacheConfiguration(TEMP_KEY, defaultCacheConfig().entryTtl(Duration.ofMinutes(FIVE)));
	}

	/*
	desc    : 해당 static method 를 사용할 경우 기본 설정을 적용한 cache manager 를 등록한다.
	refer.1 : https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#redis:support:cache-abstraction
	refer.2 : JAVA API RedisCacheConfiguration

	DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
	registerDefaultConverters(conversionService);
		> registry.addConverter(String.class, byte[].class, source -> source.getBytes(StandardCharsets.UTF_8));
		> .addConverter(SimpleKey.class, String.class, SimpleKey::toString);

	default RedisCacheConfiguration
		ttl                : Duration.ZERO (등록된 key 정보가 언제까지 유지되게 할지 설정 [캐시의 유효 시간])
		cacheNullValues    : true (캐시가 null 데이터를 허용하는지 여부를 설정, 이를 사용할 경우 null serialize 가 가능한지 확인 필요)
		usePrefix          : true (캐시에 prefix 를 적용할지 여부를 설정)
		cache prefix       : CacheKeyPrefix.simple() (:: 을 prefix 로 사용한다.)
		key serialize      : SerializationPair.fromSerializer(RedisSerializer.string()) (UTF_8 형식의 문자열 serializer 등록)
		value serialize    : SerializationPair.fromSerializer(RedisSerializer.java(classLoader)) ( JDK 의 byte serializer 등록)
		conversion service : DefaultFormattingConversionService (Money&Currency,Date-Time, Joda-Time 2.x API 등이 존재한다면 관련 converter 를 등록하는 Util class)

	@Bean
	public RedisCacheManager cacheManager() {
		return RedisCacheManager.create(connectionFactory());
	}
	*/
}
