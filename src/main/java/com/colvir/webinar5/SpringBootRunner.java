package com.colvir.webinar5;

import com.colvir.webinar5.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringBootRunner {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootRunner.class, args)) {
            AppProperties properties = applicationContext.getBean(AppProperties.class);
            System.out.println(properties.getVersion());
            System.out.println(properties.getDigit());
            System.out.println(properties.getContext().getFile());
        }
    }
}
