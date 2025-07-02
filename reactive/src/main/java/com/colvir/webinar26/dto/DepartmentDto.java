package com.colvir.webinar26.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    @NotNull
    @Size(min = 1, max = 3)
    private String id;

    @NotNull
    @Size(min = 1, max = 120)
    private String name;
}
