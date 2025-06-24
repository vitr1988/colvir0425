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
import org.springframework.cloud.bus.BusBridge;
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
//    private final ApplicationEventPublisher busClient;

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
            busClient.send(new DepositEvent(this, "AccountService", "HistoryService", accountId, money));
        }
    }

    @Override
    @Transactional
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
            busClient.send(new WithdrawEvent(this, "AccountService", "HistoryService:**", accountId, money));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountDto> findById(Long id) {
        return accountRepository.findById(id).map(accountMapper::toDto);
    }
}
