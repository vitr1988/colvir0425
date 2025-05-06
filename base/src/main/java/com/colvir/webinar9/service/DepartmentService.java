package com.colvir.webinar9.service;

import com.colvir.webinar9.dao.DepartmentDao;
import com.colvir.webinar9.dto.DepartmentDto;
import com.colvir.webinar9.mapper.DepartmentMapper;
import com.colvir.webinar9.model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentDao departmentDao;
    private final DepartmentMapper departmentMapper;

    public DepartmentDto getById(Long id) {
        return departmentMapper.toDto(departmentDao.getById(id));
    }

    public List<DepartmentDto> findAll() {
        return departmentDao.findAll().stream().map(departmentMapper::toDto).collect(Collectors.toList());
    }

    public DepartmentDto save(DepartmentDto departmentDto) {
        Long departmentId = departmentDto.getId();
        if (departmentId == null) {
            return departmentMapper.toDto(departmentDao.save(departmentMapper.toEntity(departmentDto)));
        } else {
            Department existingDepartment = departmentDao.getById(departmentId);
            departmentMapper.update(departmentDto, existingDepartment);
            return departmentMapper.toDto(departmentDao.save(existingDepartment));
        }
    }

    public DepartmentDto save(Department department) {
        return departmentMapper.toDto(departmentDao.save(department));
    }

    public void deleteById(Long id) {
        departmentDao.deleteById(id);
    }

    public void delete(DepartmentDto department) {
        departmentDao.delete(departmentMapper.toEntity(department));
    }
}
