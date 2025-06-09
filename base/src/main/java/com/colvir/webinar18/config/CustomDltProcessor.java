package com.colvir.webinar18.config;

import com.colvir.webinar18.dto.KafkaErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomDltProcessor {

    public void processDltMessage(
            String message,
            Exception exception,
            @Header(KafkaHeaders.ORIGINAL_OFFSET) byte[] offset,
            @Header(KafkaHeaders.EXCEPTION_FQCN) String descException,
            @Header(KafkaHeaders.EXCEPTION_STACKTRACE) String stacktrace,
            @Header(KafkaHeaders.EXCEPTION_MESSAGE) String errorMessage) {

        KafkaErrorResponse kafkaErrorResponse =
                KafkaErrorResponse.builder()
                        .payload(message)
                        .exceptionMessage("Error while processing kafka message :: " + errorMessage)
                        .exceptionStacktrace(stacktrace)
                        .build();
        log.error("Error while processing kafka message :: " + kafkaErrorResponse.toString());
    }
}
