package com.colvir.webinar18.service;

import com.colvir.webinar18.dto.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, EventDto> kafkaTemplate;

    public void send(String topic, EventDto event) {
        CompletableFuture<SendResult<String, EventDto>> future = kafkaTemplate.send(topic, event.getId() + "", event);
        future.whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Error while sending event! Check Kafka broker", exception);
            } else {
                log.info("Event sent {} successfully with offset {}", event, result.getRecordMetadata().offset());
            }
        });
    }
}
