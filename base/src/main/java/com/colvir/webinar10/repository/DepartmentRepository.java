package com.colvir.webinar10.repository;

import com.colvir.webinar10.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
