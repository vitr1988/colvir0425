package com.colvir.webinar4;

import com.colvir.webinar4.annotation.Blocked;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    public void deposit(String accountNumber, BigDecimal amount) {
        System.out.println("deposit");
    }

    @Blocked("Операция не должна быть выполнена")
    public void withdraw(String accountNumber, BigDecimal amount) {
        System.out.println("withdraw");
    }
}
