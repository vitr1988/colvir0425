package com.colvir.feign;

import com.colvir.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class AccountServiceFallback implements AccountServiceClient {

    @Override
    public Optional<AccountDto> getAccount(Long id) {
        return Optional.of(new AccountDto());
    }

    @Override
    public void withdrawAccount(Long id, BigDecimal sum) {
        //noop
    }
}
