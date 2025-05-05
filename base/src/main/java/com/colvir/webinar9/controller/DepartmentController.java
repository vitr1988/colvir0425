package com.colvir.webinar9.controller;

import com.colvir.webinar9.dto.DepartmentDto;
import com.colvir.webinar9.model.Department;
import com.colvir.webinar9.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/{id}")
    public DepartmentDto getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @PostMapping
    public DepartmentDto save(@RequestBody DepartmentDto departmentDto) {
        return departmentService.save(departmentDto);
    }

    @PutMapping("/{id}")
    public DepartmentDto save(@PathVariable Long id, @RequestBody Department departmentDto) {
        departmentDto.setId(id);
        return departmentService.save(departmentDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody DepartmentDto departmentDto) {
        departmentService.delete(departmentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        departmentService.deleteById(id);
    }
}
