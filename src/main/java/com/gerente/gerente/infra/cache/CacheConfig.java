package com.gerente.gerente.infra.cache;

import com.gerente.gerente.infra.cache.memory.InMemoryCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public <K, V> Cache<K, V> getCache(@Value("${app.in-memory-cache-timeout}") Long cacheTimeout) {
        return new InMemoryCache<K, V>(cacheTimeout);
    }
}
