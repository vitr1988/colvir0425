package com.colvir.service;

import com.colvir.dto.ProcessingDto;

import java.math.BigDecimal;

public interface ProcessingService {
    ProcessingDto issueCard(Long accountId);

    void spendMoney(String cardNumber, BigDecimal sum);

    ProcessingDto getProcessing(Long accountId);
}
