package com.colvir.webinar3;

import com.colvir.webinar3.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String name, String secondName) {
        Employee newEmployee = new Employee(name, secondName);
        employees.add(newEmployee);
        return newEmployee;
    }

    public void printAll() {
        System.out.println(employees);
    }

    public void printFirst() {
        System.out.println(employees.get(0));
    }
}
