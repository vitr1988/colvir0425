package com.colvir.webinar13.service;

import com.colvir.webinar13.dto.DepartmentDto;
import com.colvir.webinar13.dto.EmployeeDto;
import com.colvir.webinar13.model.Department;
import com.colvir.webinar13.model.Employee;
import com.colvir.webinar13.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    private final DepartmentService departmentService;

    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee entity = employeeDto.as();
        Optional<DepartmentDto> departmentDto = Optional.ofNullable(employeeDto.getDepartmentId())
                .flatMap(departmentService::findById);
        departmentDto
                .map(DepartmentDto::as)
                .ifPresentOrElse(
                        entity::setDepartment,
                        () -> departmentService.save(new DepartmentDto(employeeDto.getDepartmentName()))
                );
        return new EmployeeDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> findAll() {
        return repository.findAll().stream().map(EmployeeDto::new).toList();
    }
}
