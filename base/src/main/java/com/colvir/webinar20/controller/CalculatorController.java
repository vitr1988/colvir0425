package com.colvir.webinar20.controller;

import com.colvir.webinar20.serivce.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/summa")
    public int add(@RequestParam int a, @RequestParam int b) {
        return calculatorService.sum(a, b);
    }
}
