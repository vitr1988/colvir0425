package com.colvir.webinar4;

import com.colvir.webinar4.config.AppConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class BlockedRunner {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class)) {
            AccountService accountService = applicationContext.getBean(AccountService.class);
            accountService.deposit("123", new BigDecimal("1000"));
            accountService.withdraw("123", new BigDecimal("500"));
        }
    }
}
