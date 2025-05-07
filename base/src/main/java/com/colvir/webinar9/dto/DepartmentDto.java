package com.colvir.webinar9.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class DepartmentDto {

    private Long id;
    private Optional<String> name;
    private Optional<Integer> count;
}
