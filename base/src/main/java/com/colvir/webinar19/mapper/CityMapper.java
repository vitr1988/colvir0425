package com.colvir.webinar19.mapper;

import com.colvir.webinar19.dto.CityDto;
import com.colvir.webinar19.model.City;
import org.mapstruct.Mapper;

@Mapper
public interface CityMapper {
    CityDto toDto(City city);
    City toEntity(CityDto dto);
}
