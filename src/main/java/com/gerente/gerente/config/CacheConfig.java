package com.gerente.gerente.config;

import com.gerente.gerente.cache.InMemoryCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public <K, V> InMemoryCache<K, V> getInMemoryCache(@Value("${app.in-memory-cache-timeout}") Long cacheTimeout) {
        return new InMemoryCache<K, V>(cacheTimeout);
    }
}
