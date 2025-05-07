package com.colvir.webinar10.controller;

import com.colvir.webinar10.dto.EmployeeDto;
import com.colvir.webinar10.service.DepartmentEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final DepartmentEmployeeService service;

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return service.getEmployeeById(id);
    }
}
