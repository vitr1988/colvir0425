package com.colvir.webinar5.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    private Long id;
    private String number;
    private String clientName;
    private BigDecimal balance;
    private String currency;
}
