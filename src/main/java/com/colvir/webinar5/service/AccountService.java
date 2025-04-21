package com.colvir.webinar5.service;

import com.colvir.webinar5.model.Account;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class AccountService {

    @Getter
    public List<Account> accounts = new CopyOnWriteArrayList<>();

    public void add(Account account) {
        accounts.add(account);
    }

    public void remove(Account account) {
        accounts.remove(account);
    }

    public void update(Account account) {
        int index = accounts.indexOf(account);
        accounts.set(index, account);
    }
}
