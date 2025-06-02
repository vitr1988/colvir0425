package com.colvir.webinar16;

import com.colvir.webinar16.dto.NotificationDto;
import com.colvir.webinar16.service.NotificationJmsProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MqRunner {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MqRunner.class, args);
        NotificationJmsProducer producer = applicationContext.getBean(NotificationJmsProducer.class);
//        producer.sendBroadcastingNotification(new NotificationDto(1L, "Hello World", NotificationDto.NotificationType.INFORM, NotificationDto.Language.RU));
        producer.sendNotification(new NotificationDto(1L, "Hello World", NotificationDto.NotificationType.INFORM, NotificationDto.Language.RU));
    }
}
