package com.colvir.webinar14;

import com.colvir.webinar14.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.colvir.webinar14.model"})
@EnableJpaRepositories(basePackages = {"com.colvir.webinar14.repository"})
@ComponentScan(basePackages = {"com.colvir.webinar10", "com.colvir.webinar14"})
public class SecurityRunner {

    public static void main(String[] args) {
        SpringApplication.run(SecurityRunner.class, args);
    }
}
