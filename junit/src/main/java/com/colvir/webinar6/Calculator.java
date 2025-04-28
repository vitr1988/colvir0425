package com.colvir.webinar6;

public class Calculator {

    public int add(int a, int b) {
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return a + b;
    }
}
