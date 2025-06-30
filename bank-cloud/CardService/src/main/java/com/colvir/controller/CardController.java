package com.colvir.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.colvir.service.CardService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/create")
    @PreAuthorize("hasRole('CAN_CARD_GENERATE')")
//    @PreAuthorize("hasAuthority('ROLE_CAN_CARD_GENERATE')")
    String generateCardNumber(@RequestParam Long accountId) {
        log.info("Generating card number");
        return cardService.generateCardNumber(accountId);
    }
}
