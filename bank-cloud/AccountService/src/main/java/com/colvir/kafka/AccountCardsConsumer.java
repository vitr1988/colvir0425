package com.colvir.kafka;

import com.colvir.dto.CardInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Component
public class AccountCardsConsumer {

    private static final String KAFKA_CONSUMER_PARTITION_KEY_HEADER_NAME = "kafka_receivedMessageKey";

//    @Bean
//    Consumer<CardInfoDto> accountCards() {
//        return msg -> log.info("Card number {}, time: {}", msg.cardNumber(), LocalDateTime.now());
//    }

    @Bean
    Function<Message<CardInfoDto>, CardInfoDto> accountCards() {
        return msg -> {
            CardInfoDto result = msg.getPayload();
            log.info("Card number {} issued at {} for account id {}",
                    result.cardNumber(), LocalDateTime.now(), msg.getHeaders().get(KAFKA_CONSUMER_PARTITION_KEY_HEADER_NAME));
            return result;
        };
    }
}
