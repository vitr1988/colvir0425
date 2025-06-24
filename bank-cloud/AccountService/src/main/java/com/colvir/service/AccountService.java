package com.colvir.service;

import com.colvir.dto.AccountDto;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {

    Long createAccount(Long clientId);

    void depositAccount(Long accountId, BigDecimal money);

    void withdrawAccount(Long id, BigDecimal sum);

    Optional<AccountDto> findById(Long id);
}
