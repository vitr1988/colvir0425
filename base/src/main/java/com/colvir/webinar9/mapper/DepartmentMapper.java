package com.colvir.webinar9.mapper;

import com.colvir.webinar9.dto.DepartmentDto;
import com.colvir.webinar9.model.Department;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Optional;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentDto departmentDto);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    void update(DepartmentDto departmentDto, @MappingTarget Department department);

    /**
     * Получение данных из опционала
     *
     * @param optional значение для конвертации
     * @param <T>      тип для преобразования
     * @return извлеченное значение, приведенное к нужному типу
     */
    default <T> T unwrapOptional(Optional<T> optional) {
        return optional == null ? null : optional.orElse(null);
    }

    /**
     * Обертка значения в объект опционала
     *
     * @param value значения для конвертации
     * @param <T>   тип для конвертации
     * @return обернутое значение, приведенное к нужному типу
     */
    default <T> Optional<T> wrapOptional(T value) {
        return Optional.ofNullable(value);
    }
}
