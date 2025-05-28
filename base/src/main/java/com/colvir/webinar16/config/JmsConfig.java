package com.colvir.webinar16.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJms
public class JmsConfig {

    public static final String DESTINATION = "queue.example";

    public static final String JMS_TRANSACTION_MANAGER_NAME = "jmsTransactionManager";

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean(JMS_TRANSACTION_MANAGER_NAME)
    PlatformTransactionManager jmsTransactionManager(ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }

    @Bean
    @Primary
    PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
