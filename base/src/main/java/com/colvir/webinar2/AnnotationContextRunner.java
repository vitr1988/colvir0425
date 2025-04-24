package com.colvir.webinar2;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationContextRunner {

    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.colvir.webinar2");
        try (ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            Calculator calculator = applicationContext.getBean(Calculator.class);
            Calculator calculator2 = applicationContext.getBean(Calculator.class);
            Calculator calculator3 = applicationContext.getBean(Calculator.class);
            Calculator calculator4 = applicationContext.getBean(Calculator.class);
            Calculator calculator5 = applicationContext.getBean(Calculator.class);
            Calculator calculator6 = applicationContext.getBean(Calculator.class);
            System.out.println(calculator.sum(10, 15));
            System.out.println(calculator.multiply(10, 15));

            ParametrizedCalculator parametrizedCalculator = applicationContext.getBean(ParametrizedCalculator.class);
            System.out.println(parametrizedCalculator.sum());
            System.out.println(parametrizedCalculator.multiply());

            CalculatorService calculatorService = applicationContext.getBean(CalculatorService.class);
            calculatorService.process();
        }
    }
}
