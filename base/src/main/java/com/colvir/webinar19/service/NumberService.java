package com.colvir.webinar19.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NumberService {

    @Cacheable(value = "numberCache", key = "#number"
            , condition = "#number > 10"
    )
    public Long square(Long number) {
        long result = number * number;
        log.info("Now we have a square number: {} which equals: {}", number, result);
        return result;
    }
}
