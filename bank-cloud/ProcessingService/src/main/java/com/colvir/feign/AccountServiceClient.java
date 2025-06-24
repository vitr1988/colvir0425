package com.colvir.feign;

import com.colvir.web.AccountOperations;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AccountService", fallback = AccountServiceFallback.class)
public interface AccountServiceClient extends AccountOperations {
}
