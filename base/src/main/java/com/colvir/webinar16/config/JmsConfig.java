package com.colvir.webinar16.config;

import com.colvir.webinar16.service.NotificationJmsListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJms
public class JmsConfig {

    public static final String DESTINATION_QUEUE = "queue.example";

    public static final String DESTINATION_TOPIC = "topic.example";

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

    @Bean
    public JmsListenerContainerFactory<?> topicFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer,
            PlatformTransactionManager jmsTransactionManager) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        factory.setTransactionManager(jmsTransactionManager);
        factory.setSessionTransacted(true);
        return factory;
    }

//    @Bean
//    DefaultMessageListenerContainer jmsContainer(ConnectionFactory connectionFactory,
//                                                 NotificationJmsListener messageListener) {
//        DefaultMessageListenerContainer jmsContainer = new DefaultMessageListenerContainer();
//        jmsContainer.setConnectionFactory(connectionFactory);
//        jmsContainer.setDestination(new ActiveMQTopic("inbound.queue.city" + ));
//        jmsContainer.setMessageListener(messageListener);
//        return jmsContainer;
//    }
}
