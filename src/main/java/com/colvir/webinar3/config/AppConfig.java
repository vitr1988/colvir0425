package com.colvir.webinar3.config;

import com.colvir.webinar3.dao.InMemoryPersonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.colvir.webinar3")
public class AppConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Primary
    @Scope("prototype")
    InMemoryPersonDao inMemoryPersonDao() {
        return new InMemoryPersonDao();
    }

//    @Bean
//    PersonService personService(PersonDao personDao) {
//        return new PersonService(personDao);
//    }
}
