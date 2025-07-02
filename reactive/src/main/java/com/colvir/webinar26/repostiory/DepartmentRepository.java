package com.colvir.webinar26.repostiory;

import com.colvir.webinar26.model.Department;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

//ReactiveCrudRepository
public interface DepartmentRepository extends ReactiveMongoRepository<Department, String> {
//    @Query("{ id: { $exists: true }}")
//    Flux<Department> findAll(Pageable page);
}
