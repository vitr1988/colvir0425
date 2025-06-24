package com.colvir.web;

import com.colvir.dto.AccountDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountOperations {

    @GetMapping("/get/{id}")
    Optional<AccountDto> getAccount(@PathVariable Long id);

    @GetMapping("/checkout/{id}")
    void withdrawAccount(@PathVariable Long id, @RequestParam BigDecimal sum);
}
