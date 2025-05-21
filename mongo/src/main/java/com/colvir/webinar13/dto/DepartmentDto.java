package com.colvir.webinar13.dto;

import com.colvir.webinar13.model.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDto {

    private String id;
    private String name;

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
    }

    public DepartmentDto(String name) {
        this.name = name;
    }

    public Department as() {
        return new Department(id, name);
    }
}
