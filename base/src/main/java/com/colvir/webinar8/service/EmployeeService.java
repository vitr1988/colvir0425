package com.colvir.webinar8.service;

import com.colvir.webinar8.dao.EmployeeDao;
import com.colvir.webinar8.dto.EmployeeDto;
import com.colvir.webinar8.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeDao employeeDao;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto findById(long id) {
        return employeeMapper.toDto(employeeDao.findById(id));
    }

    public List<EmployeeDto> findAll() {
        return employeeMapper.toDtos(employeeDao.findAll());
    }

    public void save(EmployeeDto employee) {
        employeeDao.save(employeeMapper.toEntity(employee));
    }

    public void deleteById(Long id) {
        employeeDao.deleteBy(id);
    }

}
