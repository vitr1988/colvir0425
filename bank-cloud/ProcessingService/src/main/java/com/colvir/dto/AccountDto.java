package com.colvir.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;
    private Long clientId;
    private BigDecimal balance;
}
