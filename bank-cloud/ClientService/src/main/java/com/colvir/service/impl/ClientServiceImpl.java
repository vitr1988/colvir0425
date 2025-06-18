package com.colvir.service.impl;

import com.colvir.domain.Client;
import com.colvir.dto.ClientDto;
import com.colvir.mapper.ClientMapper;
import com.colvir.repository.ClientRepository;
import com.colvir.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public Long createClient(String name) {
        Client clientEntity = new Client(name);
        clientRepository.save(clientEntity);
        return clientEntity.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        return clientMapper.toDtos(clientRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientDto> findById(Long id) {
        return clientMapper.toOptional(clientRepository.findById(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String name) {
        clientRepository.findById(id).ifPresent(clientEntity -> clientEntity.setName(name));
    }
}
