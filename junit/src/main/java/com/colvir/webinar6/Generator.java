package com.colvir.webinar6;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Generator {

    private final InitialService service;

    public Long generate() {
        return service.getSeed() * 1000L;
    }
}
