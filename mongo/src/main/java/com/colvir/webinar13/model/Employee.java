package com.colvir.webinar13.model;

import io.github.kaiso.relmongo.annotation.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(
        collection = "employees"
)
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;

    @OneToOne
    private Department department;
}
