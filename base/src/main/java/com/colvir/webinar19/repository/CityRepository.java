package com.colvir.webinar19.repository;

import com.colvir.webinar19.model.City;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Override
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    List<City> findAll();
}
