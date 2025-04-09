package com.colvir.webinar2.impl;

import com.colvir.webinar2.ParametrizedCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("parametrizedCalculator")
public class ParametrizedCalculatorImpl implements ParametrizedCalculator {

    private int a = 0;
    private int b = 0;

    public ParametrizedCalculatorImpl() {

    }

    @Autowired
    public ParametrizedCalculatorImpl(@Qualifier("a") int a, @Qualifier("b") int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int sum() {
        return a + b;
    }

    @Override
    public int multiply() {
        return a * b;
    }
}
