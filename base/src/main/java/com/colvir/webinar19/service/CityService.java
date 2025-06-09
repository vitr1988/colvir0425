package com.colvir.webinar19.service;

import com.colvir.webinar19.dto.CityDto;

public interface CityService {
    CityDto findById(Long id);
    CityDto save(CityDto cityDto);
    void deleteById(Long id);
    void deleteAll();
}
