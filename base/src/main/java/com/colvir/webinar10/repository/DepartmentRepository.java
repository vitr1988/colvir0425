package com.colvir.webinar10.repository;

import com.colvir.webinar10.model.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static com.colvir.webinar10.model.Department.EMPLOYEE_ENTITY_GRAPH_NAME;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @EntityGraph(value = EMPLOYEE_ENTITY_GRAPH_NAME, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Department> findById(Long id);
}
