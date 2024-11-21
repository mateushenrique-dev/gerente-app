package com.gerente.gerente.infra.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class CacheAspect {
    @Autowired
    private Cache<String, Object> cache;

    @Around("@annotation(useCache)")
    public Object cacheAspect(ProceedingJoinPoint joinPoint, UseCache useCache) throws Throwable {
        Optional<Object> item = cache.get(useCache.key());

        if (item.isPresent()) {
            return item.get();
        }

        Object result = joinPoint.proceed();

        cache.put(useCache.key(), result);

        return result;
    }
}
