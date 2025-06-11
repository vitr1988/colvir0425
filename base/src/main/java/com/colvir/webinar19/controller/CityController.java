package com.colvir.webinar19.controller;

import com.colvir.webinar19.dto.CityDto;
import com.colvir.webinar19.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/{id}")
    public CityDto get(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @GetMapping
    public List<CityDto> getAll() {
        return cityService.findAll();
    }
}
