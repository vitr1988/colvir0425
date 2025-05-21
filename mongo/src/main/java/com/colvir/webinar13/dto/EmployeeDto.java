package com.colvir.webinar13.dto;

import com.colvir.webinar13.model.Department;
import com.colvir.webinar13.model.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    private String departmentId;
    private String departmentName;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.departmentId = employee.getDepartment().getId();
        this.departmentName = employee.getDepartment().getName();
    }

    public Employee as() {
        return new Employee(id, firstName, lastName, email,
                new Department(departmentId, departmentName)
        );
    }
}
