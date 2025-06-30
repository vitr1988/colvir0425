package com.colvir.service.impl;

import com.colvir.domain.Account;
import com.colvir.dto.AccountDto;
import com.colvir.dto.DepositEvent;
import com.colvir.dto.WithdrawEvent;
import com.colvir.feign.ClientServiceClient;
import com.colvir.mapper.AccountMapper;
import com.colvir.repository.AccountRepository;
import com.colvir.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.BusBridge;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
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

    private final AccountMapper accountMapper;

    private final BusBridge busClient;
//    private final ApplicationContext busClient;
//    private final ApplicationEventPublisher busClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer originHostPort;

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
        try {
            accountRepository.addBalance(accountId, money);
        } finally {
//            busClient.send(new DepositEvent(this, applicationName, null, accountId, money));
            busClient.send(new DepositEvent(this, "AccountService", "HistoryService", accountId, money));
//            busClient.publishEvent(new DepositEvent(this, applicationName, "*:**", accountId, money));
        }
    }

    @Override
    @Transactional(rollbackFor = IllegalArgumentException.class)
    public void withdrawAccount(Long accountId, BigDecimal money) {
        try {
            accountRepository.findById(accountId)
                    .map(Account::getBalance)
                    .filter(it -> it.compareTo(money) >= 0)
                    .ifPresent(
                            it ->
                                    accountRepository.addBalance(accountId, MINUS_ONE.multiply(money))
                    );
        } finally {
//            busClient.send(new WithdrawEvent(this, applicationName, null, accountId, money));
            busClient.send(new WithdrawEvent(this, applicationName, "HistoryService:**", accountId, money));
//            busClient.publishEvent(new WithdrawEvent(this, applicationName, "*:**", accountId, money));
        }
//        if (money.intValue() % 2 == 0) {
//            throw new IllegalArgumentException("Has no client with id: %d".formatted(accountId));
//        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountDto> findById(Long id) {
        return accountRepository.findById(id).map(accountMapper::toDto);
    }
}
