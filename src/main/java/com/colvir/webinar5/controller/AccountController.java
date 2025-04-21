package com.colvir.webinar5.controller;

import com.colvir.webinar5.model.Account;
import com.colvir.webinar5.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

//    private final HttpServletRequest request;
//    private final HttpServletResponse response;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts(@RequestParam String filter)  {
        System.out.println(filter);
        return ResponseEntity.ok(accountService.getAccounts());
    }
}
