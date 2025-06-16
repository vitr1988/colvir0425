package com.colvir.webinar20.serivce;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculatorService {

    private final MeterRegistry meterRegistry;

//    @Counted("summaDigits")
    @Timed("summaDigits")
    public int sum(int a, int b) {
        meterRegistry.counter("summaCounter").increment();
        return a + b;
    }
}
