package com.colvir.controller;

import com.colvir.dto.ProcessingDto;
import com.colvir.service.ProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProcessingController {

    private final ProcessingService processingService;

    @GetMapping("/issue/{accountId}")
    public ProcessingDto issueCard(@PathVariable Long accountId) {
        log.info("Issuing client with id {}", accountId);
        return processingService.issueCard(accountId);
    }

    @GetMapping("/checkout/{cardNumber}")
    public void spendMoney(@PathVariable String cardNumber, @RequestParam BigDecimal sum) {
        log.info("Spending card number {}", cardNumber);
        processingService.spendMoney(cardNumber, sum);
    }

    @GetMapping("/get")
    public ProcessingDto getProcessing(@RequestParam("account_id") Long accountId) {
        log.info("Getting client with id {}", accountId);
        return processingService.getProcessing(accountId);
    }
}
