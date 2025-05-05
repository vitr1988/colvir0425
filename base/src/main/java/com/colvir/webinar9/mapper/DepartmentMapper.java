package com.colvir.webinar9.mapper;

import com.colvir.webinar9.dto.DepartmentDto;
import com.colvir.webinar9.model.Department;
import org.mapstruct.Mapper;

@Mapper
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentDto departmentDto);
}
