package com.colvir.webinar6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private static final Calculator CALCULATOR = new  Calculator();

    @Test
    public void testSum() {
        Assertions.assertEquals(1, CALCULATOR.add(1, 0));
    }
}
