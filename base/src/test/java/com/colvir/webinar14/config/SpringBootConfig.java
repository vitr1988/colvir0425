package com.colvir.webinar14.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootConfiguration
//@ComponentScan({"com.colvir.webinar10.repository", "com.colvir.webinar14.repository"})
//@EnableJpaRepositories({"com.colvir.webinar10.repository", "com.colvir.webinar14.repository"})
public class SpringBootConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        // Set additional properties like data source, packages to scan, etc.
        em.setPackagesToScan("com.colvir.webinar10", "com.colvir.webinar14");
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        em.setDataSource(dataSource);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory dataSource) {
        return new JpaTransactionManager(dataSource);
    }
}
