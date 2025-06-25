package com.colvir.controller;

import com.colvir.dto.AbstractFinancialEvent;
import com.colvir.dto.DepositEvent;
import com.colvir.dto.OperationInfoDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.colvir.dto.OperationInfoDto.OperationType.DEPOSIT;
import static com.colvir.dto.OperationInfoDto.OperationType.WITHDRAW;

@Slf4j
@RefreshScope
@RestController
public class HistoryController implements ApplicationListener<AbstractFinancialEvent> {

    @Getter(onMethod_ = @GetMapping)
    private static final List<OperationInfoDto> historyOperations = new CopyOnWriteArrayList<>();

    @GetMapping("/config-server")
    public String getFoo(@Value("${info.foo}") String foo) {
        return foo;
    }

    @Override
    public void onApplicationEvent(AbstractFinancialEvent event) {
        log.info("Financial operation happened {}", event);
        historyOperations.add(new OperationInfoDto(event.getAccountId(), event.getSum(),
                event instanceof DepositEvent ? DEPOSIT : WITHDRAW,
                new Date(event.getTimestamp())));
    }
}
