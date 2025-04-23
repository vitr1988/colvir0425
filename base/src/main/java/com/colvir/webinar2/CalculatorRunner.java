package com.colvir.webinar2;

import com.colvir.webinar2.impl.CalculatorImpl;

public class CalculatorRunner {

    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        System.out.println(calculator.sum(10, 15));
        System.out.println(calculator.multiply(10, 15));
        System.out.println(calculator.sum(-10, 15));
        System.out.println(calculator.multiply(-10, 15));
    }
}
