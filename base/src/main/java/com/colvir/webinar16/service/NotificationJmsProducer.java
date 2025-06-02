package com.colvir.webinar16.service;

import com.colvir.webinar16.dto.NotificationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.colvir.webinar16.config.JmsConfig.DESTINATION_QUEUE;
import static com.colvir.webinar16.config.JmsConfig.DESTINATION_TOPIC;
import static com.colvir.webinar16.config.JmsConfig.JMS_TRANSACTION_MANAGER_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationJmsProducer {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Transactional(transactionManager = JMS_TRANSACTION_MANAGER_NAME)
    public void sendNotification(NotificationDto notificationDto) throws JsonProcessingException {
//        jmsTemplate.send(DESTINATION, mcc ->
//                {
//                    try {
//                        log.info("Sent notification: {}", notificationDto);
//                        return mcc.createTextMessage(objectMapper.writeValueAsString(notificationDto));
//                    } catch (JsonProcessingException e) {
//                        throw new JMSException(e.getMessage());
//                    }
//                }
//        );

//        if (jmsTemplate.isExplicitQosEnabled()) {
//            jmsTemplate.setDeliveryDelay(1000);
//        }

        jmsTemplate.convertAndSend(new ActiveMQQueue(DESTINATION_QUEUE), objectMapper.writeValueAsString(notificationDto), mcp ->
                {
                    log.info("Sent notification: {}", notificationDto);
                    mcp.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 5000); // in msec
                    mcp.setStringProperty("lang", notificationDto.getLang().name().toLowerCase());
                    return mcp;
                }
        );
    }

    @Transactional(transactionManager = JMS_TRANSACTION_MANAGER_NAME)
    public void sendBroadcastingNotification(NotificationDto notificationDto) throws JsonProcessingException {
        jmsTemplate.convertAndSend(new ActiveMQTopic(DESTINATION_TOPIC), objectMapper.writeValueAsString(notificationDto), mcp ->
                {
                    log.info("Sent notification: {}", notificationDto);
                    mcp.setStringProperty("lang", notificationDto.getLang().name().toLowerCase());
                    return mcp;
                }
        );
    }
}
