package com.colvir.webinar19.service.impl;

import com.colvir.webinar19.dto.CityDto;
import com.colvir.webinar19.service.CityService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
//@CacheConfig(cacheManager = "applicationCacheManager")
public class CachingCityServiceImpl implements CityService {

    private static final String CITY_CACHE = "cityCache";
    private final HazelcastInstance hazelcastInstance;

//    private final Cache<Long, CityDto> cache = Caffeine.newBuilder()
//            .expireAfterWrite(5, TimeUnit.MINUTES)
//            .build();

    private final CityService cityService;

    @Override
    @Cacheable(cacheNames = CITY_CACHE, key = "#id"//, cacheManager = "applicationCacheManager"
//            , cacheManager = "customCacheManager"
    )
    public CityDto findById(Long id) {
//        cache.put(id, new CityDto(id, "Murmansk"));
        log.info("Finding city with id {}", id);
        return cityService.findById(id);
    }

    @Override
    @CachePut(cacheNames = CITY_CACHE, key = "#cityDto.id"
//            , condition = "#cityDto.id > 1"
    )
//    @CachePut(cacheNames = CITY_CACHE, key = "{#root.methodName, #cityDto.id}")
    public CityDto save(CityDto cityDto) {
        IMap<Object, Object> otherCache = hazelcastInstance.getMap("otherCache");
        otherCache.put(String.valueOf(cityDto.getId()), cityDto, 1L, TimeUnit.HOURS);

        long id = hazelcastInstance.getFlakeIdGenerator("testGenerator").newId();

        log.info("Saving city {}", cityDto);
        return cityService.save(cityDto);
    }

    @Override
    @CacheEvict(cacheNames = CITY_CACHE, key = "#id")
    public void deleteById(Long id) {
        log.info("Deleting city with id {}", id);
        cityService.deleteById(id);
    }

    @Override
    @CacheEvict(cacheNames = CITY_CACHE, allEntries = true)
    public void deleteAll() {
        log.info("Deleting all cities");
        cityService.deleteAll();
    }
}
