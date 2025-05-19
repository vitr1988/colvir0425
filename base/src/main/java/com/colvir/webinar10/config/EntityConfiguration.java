package com.colvir.webinar10.config;

import com.colvir.webinar10.repository.JpaRepositoryWithEntityManagerImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan({"com.colvir.webinar10"
//        , "com.colvir.webinar9"
})
@EnableJpaRepositories(basePackages = "com.colvir.webinar10.repository", repositoryBaseClass = JpaRepositoryWithEntityManagerImpl.class)
public class EntityConfiguration {
}
