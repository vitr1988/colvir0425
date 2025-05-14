package com.colvir.webinar10.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.colvir.webinar10.model.Department.EMPLOYEE_ENTITY_GRAPH_NAME;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "departments")
@NamedEntityGraph(name = EMPLOYEE_ENTITY_GRAPH_NAME,  attributeNodes = {@NamedAttributeNode("employees")})
public class Department {

    public static final String EMPLOYEE_ENTITY_GRAPH_NAME = "Departments.employees";

    @Id
    private Long id;
    @Column(name = "dep_name")
    private String name;
    private Integer count;

    @ToString.Exclude
//    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
        this.count = 0;
    }
}
