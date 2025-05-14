package com.colvir.webinar10.controller;

import com.colvir.webinar10.dto.EmployeeDto;
import com.colvir.webinar10.pageable.OffsetLimitPageable;
import com.colvir.webinar10.service.DepartmentEmployeeService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final DepartmentEmployeeService service;

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDto> findAll(@RequestParam(required = false, defaultValue = "0") @Min(0) Long offset,
                                     @RequestParam(required = false, defaultValue = "5") @Max(100) Integer limit) {
        return service.findAll(OffsetLimitPageable.of(offset, limit));
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return service.save(employeeDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping("/{id}/email")
    public void updateEmail(@PathVariable Long id, @RequestParam String email) {
        service.updateEmail(id, email);
    }
}
