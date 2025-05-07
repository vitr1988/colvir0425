package com.colvir.webinar10;

import com.colvir.webinar10.model.Department;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppRunner {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(AppRunner.class, args)) {
            EntityManager entityManager = applicationContext.getBean(EntityManager.class);
//            System.out.println(entityManager.find(Employee.class, 2L));
            System.out.println(entityManager.find(Department.class, 1L));
        }
    }
}
