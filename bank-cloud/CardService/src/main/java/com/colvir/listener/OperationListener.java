package com.colvir.listener;

import com.colvir.dto.AbstractFinancialEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OperationListener {

    @EventListener({AbstractFinancialEvent.class})
    public void handle(AbstractFinancialEvent financialEvent) {
        log.info("Financial Event Received: {}", financialEvent);
    }
}
