package com.colvir.webinar1;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "firstName", "lastName"})
@ToString
public class Student /*extends Object implements Comparable<Student>*/ {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
}
