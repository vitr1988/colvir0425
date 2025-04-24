package com.colvir.webinar3.dao;

import com.colvir.webinar3.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {

    List<Person> findAll();

    Optional<Person> findById(Long id);

    Person save(Person person);

}
