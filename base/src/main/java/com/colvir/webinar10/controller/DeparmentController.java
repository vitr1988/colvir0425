package com.colvir.webinar10.controller;

import com.colvir.webinar10.dto.EmployeeDto;
import com.colvir.webinar10.model.HasEmployeeAttribute;
import com.colvir.webinar10.service.DepartmentEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DeparmentController {

    private final DepartmentEmployeeService service;

//
//    public List<HasEmployeeAttribute> getEmployeesByDepartmentId(@PathVariable Long id){
//        return service.getEmployeeByDepartmentId(id);
//    }
    @GetMapping("/{id}/employees")
    public List<EmployeeDto> getEmployeesByDepartmentId(@PathVariable Long id){
        return service.getEmployeeByDepartmentId(id);
    }
}
