package com.colvir.hw3.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class G {

    private Long id = new Random().nextLong();

    public Long getId() {
        return this.id;
    }
}
