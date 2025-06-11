package com.colvir.webinar20.serivce;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

//    @Counted("summaDigits")
    @Timed("summaDigits")
    public int sum(int a, int b) {
        return a + b;
    }
}
