package com.colvir.hw3.service;

import org.springframework.stereotype.Service;

@Service
public class F {

    private G g;

    public F(G g) {
        this.g = g;
    }

    public G getG() {
        return g;
    }
}
