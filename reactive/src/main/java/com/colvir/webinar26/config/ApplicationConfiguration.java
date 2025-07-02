package com.colvir.webinar26.config;

import com.colvir.webinar26.repostiory.DepartmentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = DepartmentRepository.class)
public class ApplicationConfiguration {

}
