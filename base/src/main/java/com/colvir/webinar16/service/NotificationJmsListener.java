package com.colvir.webinar16.service;

import com.colvir.webinar16.dto.NotificationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static com.colvir.webinar16.config.JmsConfig.DESTINATION_QUEUE;
import static com.colvir.webinar16.config.JmsConfig.DESTINATION_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationJmsListener {
//public class NotificationJmsListener implements MessageListener {

    private final AtomicInteger totalNotifications = new AtomicInteger(0);

    private final ObjectMapper objectMapper;

    @JmsListener(destination = DESTINATION_QUEUE, selector = "lang = 'ru'")
    public void onMessage(Message message) throws JMSException, JsonProcessingException {
        if (message instanceof TextMessage textMessage) {
            String text = textMessage.getText();
            NotificationDto notificationDto = objectMapper.readValue(text, NotificationDto.class);
            log.info("Received notification: {}", notificationDto);
            totalNotifications.incrementAndGet();
        }
    }

    @JmsListener(destination = DESTINATION_TOPIC, containerFactory = "topicFactory")
    public void onMessageTopic(TextMessage message) throws JMSException, JsonProcessingException {
        String text = message.getText();
        NotificationDto notificationDto = objectMapper.readValue(text, NotificationDto.class);
        log.info("Received notification at topic: {}", notificationDto);
    }

    public int getTotalNotifications() {
        return totalNotifications.get();
    }
}
