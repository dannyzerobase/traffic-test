package com.traffictest.board.service;

import com.traffictest.board.dto.CacheObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CacheService {

    @Cacheable(cacheNames = "caffeine", key = "#id", cacheManager = "caffeineCacheManager")
    public CacheObject getFromCaffeine(Long id){
        return makeObject(id);
    }

    @Cacheable(cacheNames = "redis", key = "#id", cacheManager = "redisCacheManager")
    public CacheObject getFromRedis(Long id){
        return makeObject(id);
    }

    private CacheObject makeObject(Long id){
        log.info("Will cached");
        return CacheObject.builder()
                .id(id)
                .key("CachedKey")
                .lastAccessedAt(LocalDateTime.now())
                .build();
    }
}