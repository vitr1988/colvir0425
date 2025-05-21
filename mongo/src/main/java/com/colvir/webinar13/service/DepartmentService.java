package com.colvir.webinar13.service;

import com.colvir.webinar13.dto.DepartmentDto;
import com.colvir.webinar13.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public DepartmentDto save(DepartmentDto departmentDto) {
        return new DepartmentDto(departmentRepository.save(departmentDto.as()));
    }

    @Transactional(readOnly = true)
    public Optional<DepartmentDto> findById(String id) {
        return departmentRepository.findById(id).map(DepartmentDto::new);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(DepartmentDto::new).toList();
    }

    @Transactional
    public void deleteById(String id) {
        departmentRepository.deleteById(id);
    }
}
