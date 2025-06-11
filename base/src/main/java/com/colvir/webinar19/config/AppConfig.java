package com.colvir.webinar19.config;

//import com.hazelcast.core.HazelcastInstance;
//import com.hazelcast.spring.cache.HazelcastCacheManager;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@EnableCaching(order = Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class AppConfig {

//    @Bean
//    @Primary
//    CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
//        return new HazelcastCacheManager(hazelcastInstance);
//    }

    @Bean
    @Primary
    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
