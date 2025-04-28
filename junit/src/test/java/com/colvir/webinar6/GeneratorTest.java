package com.colvir.webinar6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GeneratorTest {

    private Generator generator;

    @BeforeEach
    public void setUp() {
//        InitialService service = new InitialService(){
//            @Override
//            public Integer getSeed() {
//                return 1;
//            }
//        };
        InitialService service = Mockito.mock(InitialService.class);
        Mockito.when(service.getSeed()).thenReturn(1);
        generator = new Generator(service);
    }

    @Test
    public void testGenerate() {
        Assertions.assertEquals(1000L, generator.generate());
    }
}
