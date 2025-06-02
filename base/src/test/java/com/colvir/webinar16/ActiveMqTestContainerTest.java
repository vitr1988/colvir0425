package com.colvir.webinar16;

import com.colvir.webinar16.dto.NotificationDto;
import com.colvir.webinar16.service.NotificationJmsListener;
import com.colvir.webinar16.service.NotificationJmsProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

import static com.colvir.webinar16.config.JmsConfig.DESTINATION_QUEUE;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
@Testcontainers
@ContextConfiguration(classes = {
        NotificationJmsProducer.class,
        NotificationJmsListener.class,
        ActiveMqTestContainerTest.BrokerConfiguration.class
})
public class ActiveMqTestContainerTest {

    private static final String ACTIVEMQ_DOCKER_IMAGE = "symptoma/activemq";

    private static final int ACTIVE_MQ_PORT = 61616;

    private static final Duration AWAIT_TIMEOUT = Duration.ofSeconds(15);

    @Container
    private static GenericContainer<?> activeMQContainer = new GenericContainer<>(ACTIVEMQ_DOCKER_IMAGE)
            .withExposedPorts(ACTIVE_MQ_PORT)
            .withEnv("ACTIVEMQ_ENABLE_SCHEDULER", "true");
    // delayed environment

    @Autowired
    private NotificationJmsProducer producer;

    @Autowired
    private NotificationJmsListener listener;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Producer can send message for listening another bean")
    public void shouldProducerWorksCorrectly() throws JsonProcessingException, JMSException {
        String testContent = "TEST";
        NotificationDto notificationDto = new NotificationDto(testContent, NotificationDto.Language.EN);
        producer.sendNotification(notificationDto);
        Message receive = jmsTemplate.receive(DESTINATION_QUEUE);
        Assertions.assertTrue(receive instanceof TextMessage);
        TextMessage textMessage = (TextMessage) receive;
        Assertions.assertEquals(testContent, objectMapper.readValue(textMessage.getText(), NotificationDto.class).getMessage());
    }

    @Test
    public void shouldProducerAndListenerWorksCorrectlyTogether() {
        List<String> messages = List.of("TEST1", "TEST2", "TEST3");
        List<NotificationDto> list = messages.stream().map(NotificationDto::new).toList();
        Consumer<NotificationDto> sendNotificationConsumerWithSuppressException = getNotificationConsumerSuppressException();
        list.forEach(sendNotificationConsumerWithSuppressException);
        await().atMost(AWAIT_TIMEOUT).until(() -> listener.getTotalNotifications() == messages.size());
    }

    private Consumer<NotificationDto> getNotificationConsumerSuppressException() {
        Consumer<NotificationDto> sendNotificationConsumerWithSuppressException = notificationDto -> {
            try {
                producer.sendNotification(notificationDto);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };
        return sendNotificationConsumerWithSuppressException;
    }

    @Configuration
    static class BrokerConfiguration {

        private static final String BROKER_URL_TEMPLATE = "tcp://%s:%d";

        @Bean
        public JmsListenerContainerFactory createFactory() {
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            factory.setConnectionFactory(connectionFactory());
            return factory;
//            factory.setErrorHandler();
        }

        @Bean
        public ConnectionFactory connectionFactory() {
            return new ActiveMQConnectionFactory(BROKER_URL_TEMPLATE.formatted(
                    activeMQContainer.getHost(),
                    activeMQContainer.getFirstMappedPort()
            ));
        }

        @Bean
        public JmsTemplate jmsTemplate() {
            return new JmsTemplate(connectionFactory());
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }

}
