package com.colvir.webinar2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextXmlRunner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
//        Calculator calculator = (Calculator) applicationContext.getBean("calculator");
        Calculator calculator = applicationContext.getBean(Calculator.class);
        Calculator calculator2 = applicationContext.getBean(Calculator.class);
        Calculator calculator3 = applicationContext.getBean(Calculator.class);
        Calculator calculator4 = applicationContext.getBean(Calculator.class);
        Calculator calculator5 = applicationContext.getBean(Calculator.class);
        Calculator calculator6 = applicationContext.getBean(Calculator.class);
        System.out.println(calculator.sum(10, 15));
        System.out.println(calculator.multiply(10, 15));
        System.out.println(calculator.sum(-10, 15));
        System.out.println(calculator.multiply(-10, 15));

        System.out.println("-----");
        ParametrizedCalculator parametrizedCalculator = applicationContext.getBean(ParametrizedCalculator.class);
        System.out.println(parametrizedCalculator.sum());
        System.out.println(parametrizedCalculator.multiply());

    }
}
