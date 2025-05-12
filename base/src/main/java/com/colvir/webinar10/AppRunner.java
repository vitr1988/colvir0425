package com.colvir.webinar10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppRunner {
    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
//        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(AppRunner.class, args)) {
//            EntityManager entityManager = applicationContext.getBean(EntityManager.class);
////            System.out.println(entityManager.find(Employee.class, 2L));
//            System.out.println(entityManager.find(Department.class, 1L));
//        }
    }
}
