package com.colvir.webinar13.repository;

import com.colvir.webinar13.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
