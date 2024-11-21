package com.gerente.gerente.infra.cache.memory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryCache<K, V> implements InMemoryCacheInterface<K, V> {
    public static final Long DEFAULT_CACHE_TIMEOUT = 60000L;

    protected Map<K, CacheValue<V>> cacheMap;
    protected Long cacheTimeout;

    public InMemoryCache() {
        this(DEFAULT_CACHE_TIMEOUT);
    }

    public InMemoryCache(Long cacheTimeout) {
        this.cacheTimeout = cacheTimeout;
        clear();
    }

    @Override
    public void clean() {
        for(K key : getExpiredKeys()) {
            remove(key);
        }
    }

    @Override
    public void clear() {
        this.cacheMap = new HashMap<>();
    }

    @Override
    public boolean containsKey(K key) {
        return cacheMap.containsKey(key);
    }

    @Override
    public Optional<V> get(K key) {
        clean();

        System.out.println("Usando o cache");

        return Optional.ofNullable(cacheMap.get(key)).map(CacheValue::getValue);
    }

    @Override
    public void put(K key, V value) {
        clean();
        cacheMap.put(key, createCacheValue(value));
    }

    @Override
    public void remove(K key) {
        cacheMap.remove(key);
    }

    protected CacheValue<V> createCacheValue(V value) {
        LocalDateTime now = LocalDateTime.now();

        return new CacheValue<V>() {
            @Override
            public V getValue() {
                return value;
            }

            @Override
            public LocalDateTime getCreatedAt() {
                return now;
            }
        };
    }

    protected interface CacheValue<V> {
        V getValue();
        LocalDateTime getCreatedAt();
    }

    protected Set<K> getExpiredKeys() {
        return cacheMap.keySet().stream()
                .filter(this::isExpired)
                .collect(Collectors.toSet());
    }

    protected boolean isExpired(K key) {
        LocalDateTime expirationDateTime = cacheMap.get(key).getCreatedAt()
                .plus(cacheTimeout, ChronoUnit.MILLIS);

        return LocalDateTime.now().isAfter(expirationDateTime);
    }
}
