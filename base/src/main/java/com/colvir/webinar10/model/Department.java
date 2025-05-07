package com.colvir.webinar10.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "departments")
public class Department {

    @Id
    private Long id;
    @Column(name = "dep_name")
    private String name;
    private Integer count;

    @ToString.Exclude
//    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
