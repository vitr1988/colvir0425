package com.colvir.webinar10.service;

import com.colvir.webinar10.dao.DepartmentEmployeeDao;
import com.colvir.webinar10.dto.EmployeeDto;
import com.colvir.webinar10.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentEmployeeService {
    private final DepartmentEmployeeDao dao;

    public EmployeeDto getEmployeeById(Long id){
        Employee employee = dao.getEmployeeById(id);
        EmployeeDto employeeDto = mapDto(employee);
        return employeeDto;
    }

    private static EmployeeDto mapDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartmentId(employee.getDepartment().getId());
        employeeDto.setDepartmentName(employee.getDepartment().getName());
        return employeeDto;
    }

    public List<EmployeeDto> getEmployeeByDepartmentId(Long departmentId){
        List<Employee> employeesByDepartmentId = dao.getEmployeesByDepartmentId(departmentId);
        return employeesByDepartmentId.stream().map(DepartmentEmployeeService::mapDto).toList();
    }
}
