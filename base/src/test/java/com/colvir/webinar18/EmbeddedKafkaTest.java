package com.colvir.webinar18;

import com.colvir.webinar18.dto.EventDto;
import com.colvir.webinar18.service.EventListener;
import com.colvir.webinar18.service.EventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;
import java.util.Objects;

import static com.colvir.webinar18.config.KafkaConfig.TOPIC_NAME;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(brokerProperties = "localhost:9092")
class EmbeddedKafkaTest {

    @Autowired
    private EventListener consumer;

    @Autowired
    private EventProducer producer;

    @Test
    public void shouldProducerAndConsumerWorksCorrectlyTogether() {
        String data = "Hello World!";

        producer.send(TOPIC_NAME, new EventDto(data));

        await().atMost(Duration.ofSeconds(1L)).until(() ->
                Objects.equals(data, consumer.getEventDto()));
    }
}


