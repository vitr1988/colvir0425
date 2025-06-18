package com.colvir.mapper;

import com.colvir.domain.Client;
import com.colvir.dto.ClientDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ClientMapper {

    ClientDto toDto(Client clientEntity);

    List<ClientDto> toDtos(List<Client> clientEntityList);

    default Optional<ClientDto> toOptional(Optional<Client> clientEntity) {
        return clientEntity.map(this::toDto);
    }
}
