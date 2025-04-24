package com.colvir.webinar3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final String clientName;

    public ClientService(@Value("${client.name}") String clientName,
                         @Value("${digit}") int digit,
                         @Value("${context.file}") Resource resource) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void printAll() {
        System.out.println("printAll from ClientService");
    }
}
