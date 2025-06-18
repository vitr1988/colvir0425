package com.colvir.service.impl;

import com.colvir.domain.Processing;
import com.colvir.dto.ProcessingDto;
import com.colvir.feign.AccountServiceClient;
import com.colvir.feign.CardServiceClient;
import com.colvir.mapper.ProcessingMapper;
import com.colvir.repository.ProcessingRepository;
import com.colvir.service.ProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final ProcessingRepository processingRepository;

    private final AccountServiceClient accountServiceClient;
    private final CardServiceClient cardServiceClient;

    private final TransactionTemplate transactionTemplate;

    private final ProcessingMapper processingMapper;

    @Override
    public ProcessingDto issueCard(Long accountId) {
        if (accountServiceClient.getAccount(accountId).isEmpty()) {
            throw new IllegalArgumentException("Has no such account with id: " + accountId);
        }
        final String cardNumber = cardServiceClient.generateCardNumber(accountId);
        return transactionTemplate.execute(status -> {
            Processing processingEntity = new Processing(accountId, cardNumber);
            processingRepository.save(processingEntity);
            return processingMapper.toDto(processingEntity);
        });
    }

    @Override
    public void spendMoney(String cardNumber, BigDecimal sum) {
        Processing processingEntity = processingRepository.findByCard(cardNumber);
        accountServiceClient.withdrawAccount(processingEntity.getAccountId(), sum);
    }

    @Override
    @Transactional(readOnly = true)
    public ProcessingDto getProcessing(Long accountId) {
        Processing processingEntity = processingRepository.findByAccountId(accountId)
                .stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Has no such processing with account id: %d".formatted(accountId)));
        return processingMapper.toDto(processingEntity);
    }
}
