package com.colvir.dto;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class WithdrawEvent extends AbstractFinancialEvent {

    public WithdrawEvent(Object source, String originService, String destinationService, Long accountId, BigDecimal sum) {
        super(source, originService, destinationService, accountId, sum);
    }
}
