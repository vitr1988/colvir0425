package com.colvir.webinar13.model;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(
        collection = "departments"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    private String id;

    private String name;

    @GeoSpatialIndexed(name = "location", type = GeoSpatialIndexType.GEO_2D)
    private double[] location;

    @OneToMany
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
    }

    public Department(String id, String name) {
        this(name);
        this.id = id;
    }
}
