package com.colvir.webinar7.model;

import com.colvir.webinar7.dto.DepartmentDto;
import lombok.Data;

@Data
public class Employee {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Department department;
}
