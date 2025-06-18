package com.colvir.repository;

import com.colvir.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Lock balanceLock = new ReentrantLock();

    default void addBalance(Long id, BigDecimal balance) {
        balanceLock.lock();
        try {
            findById(id).map(account -> {
                account.setBalance(account.getBalance().add(balance));
                if (account.getBalance().compareTo(BigDecimal.ZERO) >= 0) {
                    return save(account);
                }
                return null;
            });
        } finally {
            balanceLock.unlock();
        }
    }
}
