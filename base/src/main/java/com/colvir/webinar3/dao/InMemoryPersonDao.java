package com.colvir.webinar3.dao;

import com.colvir.webinar3.model.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Primary
//@Component("inMemoryDao")
public class InMemoryPersonDao implements PersonDao {

    private final List<Person> people = new ArrayList<>();

    @Override
    public List<Person> findAll() {
        return people;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return people.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Person save(Person person) {
        people.add(person);
        return person;
    }

    public void init() {

    }

    public void destroy() {

    }
}
