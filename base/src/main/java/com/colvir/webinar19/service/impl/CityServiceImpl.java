package com.colvir.webinar19.service.impl;

import com.colvir.webinar19.dto.CityDto;
import com.colvir.webinar19.mapper.CityMapper;
import com.colvir.webinar19.repository.CityRepository;
import com.colvir.webinar19.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    @Transactional(readOnly = true)
    public CityDto findById(Long id) {
        return cityRepository.findById(id).map(cityMapper::toDto).orElse(null);
    }

    @Override
    @Transactional
    public CityDto save(CityDto cityDto) {
        Optional<CityDto> cityOptional = cityRepository.findById(cityDto.getId())
                .map(it -> {
                    it.setName(cityDto.getName());
                    return cityMapper.toDto(cityRepository.save(it));
                });
        return cityOptional.orElseGet(() -> cityMapper.toDto(cityRepository.save(cityMapper.toEntity(cityDto))));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        cityRepository.deleteAll();
    }
}
