package com.colvir.webinar19.repository;

import com.colvir.webinar19.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
