package com.colvir.webinar5.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {

    private Long id;
    private String number;
    private String clientName;
    private BigDecimal balance;
}
