package com.colvir.webinar2.impl;

import com.colvir.webinar2.Calculator;
import com.colvir.webinar2.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculatorServiceImpl implements CalculatorService {

    private Calculator calculator;

//    public CalculatorServiceImpl() {}
//
//    @Autowired
//    public CalculatorServiceImpl(Calculator calculator) {
//        this.calculator = calculator;
//    }

    @Autowired
    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void process() {
        System.out.println(calculator.sum(1, 2));
    }
}
