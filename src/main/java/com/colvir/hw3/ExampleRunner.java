package com.colvir.hw3;

import com.colvir.hw3.service.F;
import com.colvir.hw3.service.G;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExampleRunner {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext =
                     new AnnotationConfigApplicationContext("com.colvir.hw3")) {
            F beanF = applicationContext.getBean(F.class);
            F beanF2 = applicationContext.getBean(F.class);

            System.out.println(beanF.getG().getId());
            System.out.println(beanF2.getG().getId());
            System.out.println(beanF.getG().getId().equals(beanF2.getG().getId()));
            System.out.println("---------");

            G beanG = applicationContext.getBean(G.class);
            G beanG2 = applicationContext.getBean(G.class);
            System.out.println(beanG.getId());
            System.out.println(beanG2.getId());
            System.out.println(beanG.getId().equals(beanG2.getId()));
        }
    }
}
