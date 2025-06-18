package com.colvir.feign;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class CardServiceFallback implements CardServiceClient {

    @Override
    public String generateCardNumber(@RequestParam Long accountId) {
        return StringUtils.EMPTY;
    }
}
