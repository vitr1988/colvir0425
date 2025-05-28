package com.colvir.webinar16.service;

import com.colvir.webinar16.dto.NotificationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.colvir.webinar16.config.JmsConfig.DESTINATION;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationJmsListener {

    private final ObjectMapper objectMapper;

    @JmsListener(destination = DESTINATION, selector = "lang = 'ru'")
    public void onMessage(Message message) throws JMSException, JsonProcessingException {
        if (message instanceof TextMessage textMessage) {
            String text = textMessage.getText();
            NotificationDto notificationDto = objectMapper.readValue(text, NotificationDto.class);
            log.info("Received notification: {}", notificationDto);
        }
    }
}
