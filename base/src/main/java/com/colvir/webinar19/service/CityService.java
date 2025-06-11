package com.colvir.webinar19.service;

import com.colvir.webinar19.dto.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> findAll();
    CityDto findById(Long id);
    CityDto save(CityDto cityDto);
    void deleteById(Long id);
    void deleteAll();
}
