package com.colvir.webinar8.model;

import lombok.Data;

@Data
public class Employee {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Department department;
}
