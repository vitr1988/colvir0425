package com.colvir.webinar2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.colvir.webinar2")
public class ApplicationConfig {

    @Bean("a")
    public int a() {
        return 10;
    }

    @Bean("b")
    public int b() {
        return 100;
    }

//    @Bean
//    public ParametrizedCalculator parametrizedCalculator() {
//        return new ParametrizedCalculatorImpl(1, 10);
//    }
}
