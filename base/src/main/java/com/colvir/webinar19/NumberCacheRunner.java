package com.colvir.webinar19;

import com.colvir.webinar19.service.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class NumberCacheRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NumberCacheRunner.class, args);
        NumberService numberService = applicationContext.getBean(NumberService.class);
        log.info("Result of calculation with arg {} = {}", 10L, numberService.square(10L));
        log.info("Result of calculation {}", numberService.square(10L));
        log.info("Result of calculation {}", numberService.square(100L));
        log.info("Result of calculation {}", numberService.square(100L));
        log.info("Result of calculation {}", numberService.square(100L));
        log.error("Result of calculation {}", numberService.square(100L), new Throwable());
    }
}
