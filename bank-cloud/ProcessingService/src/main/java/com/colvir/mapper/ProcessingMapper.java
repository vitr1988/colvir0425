package com.colvir.mapper;

import com.colvir.domain.Processing;
import com.colvir.dto.ProcessingDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProcessingMapper {

    ProcessingDto toDto(Processing entity);
}
