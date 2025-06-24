package com.colvir.dto;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class DepositEvent extends AbstractFinancialEvent {

    public DepositEvent(Object source, String originService, String destinationService, Long accountId, BigDecimal sum) {
        super(source, originService, destinationService, accountId, sum);
    }
}
