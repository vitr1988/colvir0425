package com.colvir.webinar8.mapper;

import com.colvir.webinar8.dto.EmployeeDto;
import com.colvir.webinar8.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface EmployeeMapper {

    Employee toEntity(EmployeeDto employeeDto);
    EmployeeDto toDto(Employee employee);

    default List<EmployeeDto> toDtos(List<Employee> employees) {
        return employees.stream().map(this::toDto).collect(Collectors.toList());
    }
}
