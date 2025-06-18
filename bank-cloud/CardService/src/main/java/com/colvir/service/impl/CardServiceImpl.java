package com.colvir.service.impl;

import com.colvir.dto.CardInfoDto;
import com.colvir.generator.CardNumberGenerator;
import com.colvir.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private static final String KAFKA_PRODUCER_PARTITION_KEY_HEADER_NAME = "kafka_messageKey";
    private static final String NOTIFICATIONS_OUT = "notifications";

    private final CardNumberGenerator cardNumberGenerator;
    private final StreamBridge streamBridge;

    @Override
    public String generateCardNumber(Long accountId) {
        final String cardNumber = cardNumberGenerator.generate();
//        streamBridge.send(NOTIFICATIONS_OUT, new CardInfoDto(cardNumber));
        streamBridge.send(NOTIFICATIONS_OUT, MessageBuilder.withPayload(new CardInfoDto(cardNumber))
                .setHeader(KAFKA_PRODUCER_PARTITION_KEY_HEADER_NAME, accountId + "")
                .build());
        return cardNumber;
    }
}
