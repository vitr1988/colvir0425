package com.colvir.listener;

import com.colvir.dto.AbstractFinancialEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FinancialOperationListener implements ApplicationListener<AbstractFinancialEvent> {

    @Override
    public void onApplicationEvent(AbstractFinancialEvent event) {
        log.info("FinancialOperationListener received event: {}", event);
    }
}
