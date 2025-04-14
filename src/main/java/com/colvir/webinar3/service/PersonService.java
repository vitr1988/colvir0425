package com.colvir.webinar3.service;

import com.colvir.webinar3.dao.PersonDao;
import com.colvir.webinar3.model.Person;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.Random;

//@Component
@Service
public class PersonService implements InitializingBean {

    private static final Random RANDOM = new Random();

    private final PersonDao personDao;
//    private final PersonService personService;

    public PersonService(Map<String, PersonDao> personDao
//            , @Lazy PersonService personService
    ) {
        System.out.println("PersonService constructor called");
        this.personDao = personDao.values().stream().findFirst().orElse(null);
//        this.personService = personService;
    }

    public void save(Person person) {
        if (person.getId() == null) {
            person.setId(RANDOM.nextLong());
        }
        personDao.save(person);
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }

    @PostConstruct
    public void init() {
        System.out.println("Бин сервиса готов");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Бин сервиса удаляется");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
