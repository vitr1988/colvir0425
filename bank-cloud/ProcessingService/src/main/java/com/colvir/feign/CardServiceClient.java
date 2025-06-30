package com.colvir.feign;

import com.colvir.config.OAuth2FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CardService", fallback = CardServiceFallback.class
        , configuration = OAuth2FeignConfig.class
)
public interface CardServiceClient {

    @GetMapping("/create")
    String generateCardNumber(@RequestParam Long accountId);
}
