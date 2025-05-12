package com.colvir.webinar10.dao;

import com.colvir.webinar10.model.Employee;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentEmployeeDao {

    private final EntityManager em;

    public Employee getEmployeeById(Long id) {
        return em.find(Employee.class, id);
    }

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            em.persist(employee);
            return employee;
        } else {
            Employee merge = em.merge(employee);
            em.flush();
            return merge;
        }
    }

    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        return em.createQuery(
                """
                        select e from Employee e
                        join fetch e.department d
                        where d.id = :departmentId
                        """
//                        """
//                        select e from Department d
//                        join d.employees e
//                        where d.id = :departmentId
//                        """
                , Employee.class
        ).setParameter("departmentId", departmentId).getResultList();
//        return em.find(Department.class, departmentId).getEmployees();
    }
}
