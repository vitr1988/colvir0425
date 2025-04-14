package com.colvir.webinar3.dao;

import com.colvir.webinar3.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Component("jdbcDao")
@Repository("jdbcDao")
public class JdbcPersonDao implements PersonDao {

    @Override
    public List<Person> findAll() {
        return List.of();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Person save(Person person) {
        return null;
    }
}
