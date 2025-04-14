package com.colvir.webinar3.config;

import com.colvir.webinar3.service.PersonService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(PersonService.class)
@PropertySource("classpath:config.properties")
public class ResourceConfiguration {

}
