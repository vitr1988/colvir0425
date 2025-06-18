package com.colvir.controller;

import com.colvir.dto.ClientDto;
import com.colvir.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/create")
    public Long createClient(@RequestParam String name) {
        log.info("Creating a new client with name {}", name);
        return clientService.createClient(name);
    }

    @GetMapping("/get")
    public List<ClientDto> findAll() {
        log.info("Retrieving all clients");
        return clientService.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<ClientDto> findById(@PathVariable Long id) {
        log.info("Retrieving client with id {}", id);
        return clientService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Deleting client with id {}", id);
        clientService.deleteById(id);
    }

    @GetMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestParam String name) {
        log.info("Updating client with id {}", id);
        clientService.update(id, name);
    }
}
