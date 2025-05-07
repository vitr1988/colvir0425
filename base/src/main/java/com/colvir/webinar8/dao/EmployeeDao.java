package com.colvir.webinar8.dao;

import com.colvir.webinar8.model.Department;
import com.colvir.webinar8.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeDao {

    private final JdbcOperations jdbcTemplate;
//    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Employee findById(long id) {
        return jdbcTemplate.queryForObject(
                //language=SQL
                """
                select emp.id, emp.name, emp.last_name, emp.email,
                dep.id as dep_id, dep.name as dep_name
                from employees emp
                join departments dep on emp.department_id = dep.id
                where emp.id = :id
                """, (rs, rowNum) -> {
                    Employee employee = new Employee();
                    employee.setId(rs.getLong("id"));
                    employee.setName(rs.getString("name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));
                    Department department = new Department();
                    department.setId(rs.getInt("dep_id"));
                    department.setName(rs.getString("dep_name"));
                    employee.setDepartment(department);
                    return employee;
                }, id);
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query(
                //language=SQL
                """
                select emp.id, emp.name, emp.last_name, emp.email,
                dep.id as dep_id, dep.name as dep_name
                from employees emp
                join departments dep on emp.department_id = dep.id
                order by emp.id
                """, (rs, rowNum) -> {
                    Employee employee = new Employee();
                    employee.setId(rs.getLong("id"));
                    employee.setName(rs.getString("name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));
                    Department department = new Department();
                    department.setId(rs.getInt("dep_id"));
                    department.setName(rs.getString("dep_name"));
                    employee.setDepartment(department);
                    return employee;
                });
    }

    public void save(Employee employee) {
        if (employee.getId() == null) {
            jdbcTemplate.update("""
                insert into employees(name, last_name, email, department_id)
                values (?, ?, ?, ?)
            """, employee.getName(), employee.getLastName(), employee.getEmail(), employee.getDepartment().getId());
        }
        else {
            jdbcTemplate.update("""
                update employees set name = ?, last_name = ?, email = ?, department_id = ?
                where id = ?
            """, employee.getName(), employee.getLastName(),
                    employee.getEmail(), employee.getDepartment().getId(),
                    employee.getId());
        }
    }

    public void deleteBy(long id) {
        jdbcTemplate.update("""
                delete from employees
                where id = ?
            """, id);
    }

}
