package com.colvir.listener;

import com.colvir.dto.WithdrawEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class WithdrawListener {

    @TransactionalEventListener(value = WithdrawEvent.class, phase = TransactionPhase.AFTER_ROLLBACK)
    public void onEvent(WithdrawEvent withdrawEvent) {
        log.info("Withdraw Event Received: {}", withdrawEvent);
    }
}
