package com.colvir.webinar4;

import com.colvir.webinar4.service.PrintService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootRunner {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootRunner.class, args)) {
            applicationContext.getBean(PrintService.class).print("Hello World!");
        }
    }
}
