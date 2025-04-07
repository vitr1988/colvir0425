package com.colvir.webinar1;

import java.util.Objects;

public class Student /*extends Object implements Comparable<Student>*/ {

    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public Student() {

    }

    public Student(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student student) {
            return this.id == student.id
                    && this.firstName.equals(student.firstName)
                    && this.lastName.equals(student.lastName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

//    @Override
//    public int compareTo(Student o) {
//        return 0;
//    }
}
