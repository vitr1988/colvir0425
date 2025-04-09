package com.colvir.webinar2.impl;

import com.colvir.webinar2.Calculator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CalculatorImpl implements Calculator {

    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }
}
