package com.colvir.dto;

import java.math.BigDecimal;
import java.util.Date;

public record OperationInfoDto(
        Long accountId,
        BigDecimal money,
        OperationType type,
        Date timestamp) {

    public enum OperationType {
        DEPOSIT, WITHDRAW
    }
}
