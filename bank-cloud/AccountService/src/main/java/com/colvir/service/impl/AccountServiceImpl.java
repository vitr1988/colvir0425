package com.colvir.service.impl;

import com.colvir.domain.Account;
import com.colvir.feign.ClientServiceClient;
import com.colvir.repository.AccountRepository;
import com.colvir.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final BigDecimal MINUS_ONE = BigDecimal.valueOf(-1);

    private final AccountRepository accountRepository;
    private final ClientServiceClient clientServiceClient;
    private final TransactionTemplate transactionTemplate;

    @Override
    public Long createAccount(Long clientId) {
        if (clientServiceClient.findById(clientId).isEmpty()) {
            throw new IllegalArgumentException("Has no client with id: %d".formatted(clientId));
        }
        return transactionTemplate.execute(status -> {
            Account accountEntity = new Account(clientId);
            accountRepository.save(accountEntity);
            return accountEntity.getId();
        });
    }

    @Override
    @Transactional
    public void depositAccount(Long accountId, BigDecimal money) {
        accountRepository.addBalance(accountId, money);
    }

    @Override
    @Transactional
    public void withdrawAccount(Long accountId, BigDecimal money) {
        accountRepository.addBalance(accountId, MINUS_ONE.multiply(money));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }
}
