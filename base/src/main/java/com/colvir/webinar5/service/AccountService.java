package com.colvir.webinar5.service;

import com.colvir.webinar5.dto.AccountDto;
import com.colvir.webinar5.mapper.AccountMapper;
import com.colvir.webinar5.model.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Getter
    public List<Account> accounts = List.of(new Account(1L, "1111", "Vitalii", BigDecimal.ZERO));//new CopyOnWriteArrayList<>();

    private final AccountMapper accountMapper;

    private AtomicLong counter = new AtomicLong();

    public Optional<AccountDto> getById(Long id) {
        return accounts.stream().filter(account -> account.getId().equals(id)).findFirst().map(accountMapper::toDto);
    }

    public void save(AccountDto account) {
        if (account.getId() == null) {
            account.setId(counter.incrementAndGet());
            accounts.add(accountMapper.toEntity(account));
        }
        else {
            getById(account.getId())
                    .map(accountMapper::toEntity)
                    .ifPresent(it -> accounts.set(accounts.indexOf(it), accountMapper.toEntity(account)));
        }
    }

    public Optional<?> delete(AccountDto account) {
        boolean remove = accounts.remove(accountMapper.toEntity(account));
        return remove ? Optional.of(account) : Optional.empty();
    }
}
