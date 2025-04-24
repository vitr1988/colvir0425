package com.colvir.webinar5.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    @Min(0)
    private Long id;
    @NotEmpty
    private String number;
    @NotEmpty
    private String clientName;
    private BigDecimal balance;
    private String cur;
}
