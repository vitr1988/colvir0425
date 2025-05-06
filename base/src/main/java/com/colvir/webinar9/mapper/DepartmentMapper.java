package com.colvir.webinar9.mapper;

import com.colvir.webinar9.dto.DepartmentDto;
import com.colvir.webinar9.model.Department;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentDto departmentDto);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    void update(DepartmentDto departmentDto, @MappingTarget Department department);
}
