package com.colvir.webinar26.service;

import com.colvir.webinar26.model.Department;
import com.colvir.webinar26.repostiory.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.channels.FileChannel;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Flux<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Transactional
    public Mono<Void> deleteById(String id) {
        return departmentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Mono<Department> findById(String departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Transactional
    public Mono<Department> save(Department entity) {
        return departmentRepository.save(entity);
    }
}
