package com.colvir.webinar3;

import com.colvir.webinar3.config.ApplicationConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);

            System.out.println(employeeService.add("Ivan", "Ivanov"));

            System.out.println(employeeService.add("Petr", "Petrov"));

            employeeService.printAll();
        }
    }
}
