package com.colvir.webinar5.controller;

import com.colvir.webinar5.dto.AccountDto;
import com.colvir.webinar5.dto.AccountDto;
import com.colvir.webinar5.mapper.AccountMapper;
import com.colvir.webinar5.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

//    private final HttpServletRequest request;
//    private final HttpServletResponse response;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts(@RequestParam Optional<String> filter) {
        filter.ifPresent(System.out::println);
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable Long id) {
        return accountService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid AccountDto account) {
        if (account.getId() != null) {
            throw new IllegalArgumentException("Account id should be null");
        }
        accountService.save(account);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid AccountDto account) {
        account.setId(id);
        accountService.save(account);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return accountService.getById(id)
                .map(accountService::delete)
                .map(it -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

}