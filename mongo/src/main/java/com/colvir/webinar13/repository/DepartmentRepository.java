package com.colvir.webinar13.repository;

import com.colvir.webinar13.model.Department;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DepartmentRepository extends MongoRepository<Department, String> {

    // Metric: {'geoNear' : 'location', 'near' : [x, y], 'minDistance' : min,
    //          'maxDistance' : max, 'distanceMultiplier' : metric.multiplier,
    //          'spherical' : true }
    List<GeoResult<Department>> findByLocationNear(Point location, Distance distance);

}
