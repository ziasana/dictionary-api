package org.example.dictionaryapi.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisCacheConfig {

        @Value("${cache.ttl:3600}")
        private long defaultTtl;

        @Bean
        public RedisConnectionFactory redisConnectionFactory() {
            return new LettuceConnectionFactory();
        }

        @Bean
        public RedisCacheConfiguration cacheConfiguration() {

            return RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofMinutes(10))
                    .disableCachingNullValues()
                    .serializeValuesWith(
                            RedisSerializationContext.SerializationPair.fromSerializer(
                                    RedisSerializer.json()
                            )
                    );
        }

        @Bean
        public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
            return RedisCacheManager.builder(connectionFactory)
                    .cacheDefaults(cacheConfiguration())
                    .build();
        }
}
