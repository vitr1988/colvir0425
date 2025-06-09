package com.colvir.webinar18.service;

import com.colvir.webinar18.dto.EventDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import static com.colvir.webinar18.config.KafkaConfig.TOPIC_NAME;

@Slf4j
@Component
public class EventListener {

    @Getter
    private EventDto eventDto;

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(multiplier = 2, value = 1000)
    )
    @KafkaListener(topics = TOPIC_NAME, concurrency = "2"//, containerFactory = "kafkaListenerContainerFactory"
//            topicPartitions = {
//            @TopicPartition(topic = TOPIC_NAME, partitions = {"0", "1"}),
//    }
    )
    public void handleEvent(
//            ConsumerRecord<?, ?> record
            @Payload EventDto event
    ) {
        log.info("Received event {}", event);
        this.eventDto = event;
    }
}
