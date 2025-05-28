package com.colvir.webinar16.controller;

import com.colvir.webinar16.dto.NotificationDto;
import com.colvir.webinar16.service.NotificationJmsProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class MqController {

    private final NotificationJmsProducer notificationJmsProducer;

    @PostMapping
    @SneakyThrows(JsonProcessingException.class)
    public void sendNotification(@RequestBody NotificationDto notificationDto) {
        notificationJmsProducer.sendNotification(notificationDto);
    }
}
