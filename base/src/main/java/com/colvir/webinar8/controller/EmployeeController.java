package com.colvir.webinar8.controller;

import com.colvir.webinar8.dto.EmployeeDto;
import com.colvir.webinar8.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    public final EmployeeService employeeService;

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @PutMapping("/{id}")
    public void save(@RequestBody EmployeeDto dto, @PathVariable Long id) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalArgumentException("Invalid ID");
        }
        employeeService.save(dto);
    }

    @PostMapping
    public void save(@RequestBody EmployeeDto dto) {
        if (!Objects.isNull(dto.getId())) {
            throw new IllegalArgumentException("Shouldn't define ID");
        }
        employeeService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}

