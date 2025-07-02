package com.colvir.webinar26.mapper;

import com.colvir.webinar26.dto.DepartmentDto;
import com.colvir.webinar26.model.Department;
import org.mapstruct.Mapper;

@Mapper
public interface DepartmentMapper {
    DepartmentDto toDto(Department entity);
    Department toEntity(DepartmentDto entity);
}
