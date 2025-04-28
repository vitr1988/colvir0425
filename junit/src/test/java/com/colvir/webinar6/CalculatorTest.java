package com.colvir.webinar6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@DisplayName("Калькулятор должен уметь ")
public class CalculatorTest {

    private static final Calculator underTest = new Calculator();

    @Test
    @Order(1)
    @DisplayName("правильно расчитывать сумму значений")
    public void shouldCalculatorSumming() {
        Assertions.assertEquals(1, underTest.add(1, 0));
        Assertions.assertEquals(0, underTest.add(1, -1));
        Assertions.assertEquals(-1, underTest.add(1, -2));
    }

    @Test
    @Order(4)
    @DisplayName("реагировать на отсутствие исключений")
    public void shouldCalculatorDoesntThrowException() {
        Assertions.assertDoesNotThrow(() -> underTest.add(1, 0));
    }

    @Disabled
    @Test
    @Order(2)
    public void shouldCalculatorThrowException() {
        Assertions.assertThrows(RuntimeException.class, () -> underTest.add(1, 0));
    }

    @Test
    @Order(3)
    public void shouldCalculatorExpectDelay() {
        Duration delay = Duration.ofSeconds(1);
        Assertions.assertTimeoutPreemptively(delay, () -> underTest.add(1, 0));
    }

}
