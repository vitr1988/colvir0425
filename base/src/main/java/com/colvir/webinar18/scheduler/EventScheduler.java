package com.colvir.webinar18.scheduler;

import com.colvir.webinar18.dto.EventDto;
import com.colvir.webinar18.service.EventProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static com.colvir.webinar18.config.KafkaConfig.TOPIC_NAME;

@Component
@RequiredArgsConstructor
public class EventScheduler {

    private final EventProducer eventProducer;
    private final AtomicInteger counter = new AtomicInteger(0);

    @Scheduled(fixedDelay = 10_000)
    public void run() {
        eventProducer.send(TOPIC_NAME, new EventDto("New Event %d".formatted(counter.incrementAndGet())));
    }
}
