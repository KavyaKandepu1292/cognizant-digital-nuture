package com.example.apigateway.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    /** In-memory Caffeine cache, 30s TTL, used by CachingFilter for GET responses. */
    @Bean
    public Cache<String, String> responseCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .maximumSize(1_000)
                .build();
    }
}
