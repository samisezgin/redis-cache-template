package com.samisezgin.cache.redis.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class RedisConfiguration {
	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private int redisPort;

	@Bean
	LettuceConnectionFactory redisConnectionFactory() {

		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);

		return new LettuceConnectionFactory(configuration);
	}

	@Bean
	RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration cacheConfig = defaultCacheConfig(Duration.ofMinutes(5));
		return RedisCacheManager.create(connectionFactory);
	}

	private RedisCacheConfiguration defaultCacheConfig(Duration ofMinutes) {

		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(ofMinutes)
				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}

}