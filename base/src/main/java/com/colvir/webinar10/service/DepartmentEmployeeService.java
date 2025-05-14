package com.colvir.webinar10.service;

import com.colvir.webinar10.dao.DepartmentEmployeeDao;
import com.colvir.webinar10.dto.EmployeeDto;
import com.colvir.webinar10.model.Department;
import com.colvir.webinar10.model.Employee;
import com.colvir.webinar10.model.HasEmployeeAttribute;
import com.colvir.webinar10.repository.DepartmentRepository;
import com.colvir.webinar10.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class DepartmentEmployeeService {
//    private final DepartmentEmployeeDao dao;

    private final TransactionTemplate transactionTemplate;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private DepartmentEmployeeService self;

//    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Long id){
//        Employee employee = dao.getEmployeeById(id);
//        transactionTemplate.execute(status -> {
//            //
//        });
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));
        EmployeeDto employeeDto = self.mapDto(employee);
        return employeeDto;
    }

//    @Transactional(rollbackFor = IOException.class, noRollbackFor = NullPointerException.class)//(propagation = Propagation.REQUIRES_NEW)
    public EmployeeDto mapDto(final Employee employee) {
        employee.setEmail("test@test.com");
        transactionTemplate.executeWithoutResult(status -> {
            employeeRepository.save(employee);
        });
        Department department = employee.getDepartment();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartmentId(department.getId());
        employeeDto.setDepartmentName(transactionTemplate.execute(status -> department.getName()));
        return employeeDto;
    }

    public Employee mapEntity(EmployeeDto employee) {
        Employee employeeEntity = new Employee();
        employeeEntity.setId(employee.getId());
        employeeEntity.setName(employee.getName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmail(employee.getEmail());

        Department department = departmentRepository.findById(employee.getDepartmentId())
                .orElseGet(() ->
                        departmentRepository.save(new Department(employee.getDepartmentId(), employee.getDepartmentName()))
                );
        employeeEntity.setDepartment(department);
        return employeeEntity;
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> getEmployeeByDepartmentId(Long departmentId){
//        List<Employee> employeesByDepartmentId = employeeRepository.findByDepartmentId(departmentId);
//        return employeesByDepartmentId.stream().map(this::mapDto).toList();
        return departmentRepository.findById(departmentId)
                .map(Department::getEmployees)
                .stream()
                .flatMap(List::stream)
                .map(this::mapDto).toList();
//        return employeeRepository.findByDepId(departmentId);
    }

    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto){
        Employee employee = mapEntity(employeeDto);
        employee = employeeRepository.save(employee);
        return mapDto(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> getEmployees() {
        return employeeRepository.findAll().stream().map(this::mapDto).toList();
    }

    @Transactional
    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }

    @Transactional
    public void deleteByNameAndLastName(String name, String lastName){
        employeeRepository.deleteByNameAndLastName(name, lastName);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> findAll(Pageable pageable){
        return employeeRepository.findAll(pageable).map(this::mapDto).toList();
    }

    @Transactional
    public void updateEmail(Long id, String email) {
        employeeRepository.findById(id).ifPresent(it -> {
            it.setEmail(email);
            employeeRepository.save(it);
        });
    }
}
