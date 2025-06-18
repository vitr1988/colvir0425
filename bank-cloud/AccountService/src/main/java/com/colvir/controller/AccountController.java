package com.colvir.controller;

import com.colvir.domain.Account;
import com.colvir.service.AccountService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private static final String ACCOUNT_CIRCUIT_BREAKER_NAME = "account";

    private final AccountService accountService;

    @GetMapping("/create")
    public Long createAccount(@RequestParam("client_id") Long clientId) {
        log.info("Creating client with id {}", clientId);
        return accountService.createAccount(clientId);
    }

    @GetMapping("/fund/{id}")
    public void depositAccount(@PathVariable Long id, @RequestParam BigDecimal sum) {
        log.info("Depositing client with id {}", id);
        accountService.depositAccount(id, sum);
    }

    @GetMapping("/checkout/{id}")
    public void withdrawAccount(@PathVariable Long id, @RequestParam BigDecimal sum) {
        log.info("Withdrawing client with id {}", id);
        accountService.withdrawAccount(id, sum);
    }

    @GetMapping("/get/{id}")
    public Optional<Account> getAccount(@PathVariable Long id) {
        log.info("Getting client with id {}", id);
        return accountService.findById(id);
    }

    @Async
    @GetMapping("/fallback/{id}")
    @TimeLimiter(name = ACCOUNT_CIRCUIT_BREAKER_NAME)
    @Bulkhead(name = ACCOUNT_CIRCUIT_BREAKER_NAME, type = Bulkhead.Type.THREADPOOL)
    @CircuitBreaker(name = ACCOUNT_CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackMethod")
    public CompletableFuture<Long> fallback(@PathVariable Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Has no accounts with negative ids");
        }
        return CompletableFuture.completedFuture(id);
    }

    private CompletableFuture<Long> fallbackMethod(Long id, Exception e) {
        log.warn("Falling back method with id {}", id, e);
        return CompletableFuture.completedFuture(-1L);
    }
}
