package com.colvir.webinar10.repository;

import com.colvir.webinar10.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
                select e from Employee e
                where e.department.id = :departmentId
            """)
    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByDepartmentName(String name);

//    @Query("""
//                select e from Employee e
//                where e.email = :email or e.name = :name
//            """)
    List<Employee> findByEmailOrName(String email, String name);

    @Query("""
            select e from Employee e
            where e.email = :email
                    or e.name = :name
                    or e.lastName = :lastName
        """)
    List<Employee> findByCriteria(String email, String name, String lastName);

    @Query("""
        delete from Employee e
        where e.lastName = :lastName and e.name = :name
    """)
    @Modifying
    void deleteByNameAndLastName(String name, String lastName);

    @Query("""
            update Employee e
            set e.lastName = :lastName, e.name = :name
            where e.id = :id
    """)
    @Modifying
    void updateAttributeById(Long id, String name, String lastName);
}
