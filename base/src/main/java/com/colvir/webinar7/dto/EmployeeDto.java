package com.colvir.webinar7.dto;

import lombok.Data;

@Data
public class EmployeeDto {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private DepartmentDto department;
}
