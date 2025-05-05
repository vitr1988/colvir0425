package com.colvir.webinar9.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table(name = "departments")
public class Department {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deparment_seq")
    @SequenceGenerator(name = "deparment_seq",
            sequenceName = "departments_seq",
            allocationSize = 1)
    private Long id;

    @Column(name = "dep_name")
    private String name;

    private Integer count;
}
