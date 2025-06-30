package com.colvir.feign;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CardServiceFallback implements CardServiceClient {

    @Override
    public String generateCardNumber(Long accountId) {
        return StringUtils.EMPTY;
    }
}
