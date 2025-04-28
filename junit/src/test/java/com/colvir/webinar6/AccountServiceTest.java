package com.colvir.webinar6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AccountServiceTest {

    private static AccountService underTest;

//    @BeforeEach
    @BeforeAll
    public static void setup() {
        underTest = new AccountService();
    }

    @Test
    public void testCalculation() {
        Assertions.assertEquals(1, underTest.calculate());
//        Assertions.assertEquals(1, underTest.calculate());
    }

}
