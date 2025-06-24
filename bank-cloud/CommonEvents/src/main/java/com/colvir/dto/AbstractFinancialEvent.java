package com.colvir.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public abstract class AbstractFinancialEvent extends RemoteApplicationEvent {

    private Long accountId;

    private BigDecimal sum;

    public AbstractFinancialEvent(Object source, String originService, String destinationService, Long accountId, BigDecimal sum) {
        super(source, originService, destinationService);
        this.accountId = accountId;
        this.sum = sum;
    }
}
