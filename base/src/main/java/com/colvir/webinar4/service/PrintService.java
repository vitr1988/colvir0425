package com.colvir.webinar4.service;

import com.colvir.webinar4.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PrintService {

    private AccountService accountService;

    private PrintService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void print(String message) {
        accountService.deposit("112", BigDecimal.ZERO);
        System.out.println(message);
    }
}
