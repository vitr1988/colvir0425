package com.colvir.webinar13.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(
        collection = "employees"
)
public class Employee {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;

//    private Department department;
}
