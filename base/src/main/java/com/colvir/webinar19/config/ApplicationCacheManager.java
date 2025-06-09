package com.colvir.webinar19.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Slf4j
@Component
public class ApplicationCacheManager implements CacheManager {

    private Map<String, Cache> caches;

    @PostConstruct
    public void init() {
        caches = new ConcurrentHashMap<>();
    }

    @Override
    public Cache getCache(String name) {
        return caches.computeIfAbsent(getLocale().toString(), k -> new ConcurrentMapCache(name));
    }

    @Override
    public Collection<String> getCacheNames() {
        return caches.keySet();
    }

    @PreDestroy
    public void clear() {
        caches.clear();
    }
}

