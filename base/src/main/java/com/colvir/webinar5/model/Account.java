package com.colvir.webinar5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;
    private String number;
    private String clientName;
    private BigDecimal balance;
    private String currency;
}
