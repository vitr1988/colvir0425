package com.colvir.webinar3;

import com.colvir.webinar3.config.AppConfig;
import com.colvir.webinar3.model.Person;
import com.colvir.webinar3.service.ClientService;
import com.colvir.webinar3.service.PersonService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppRunner {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext annotationConfigApplicationContext =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            PersonService personService = annotationConfigApplicationContext.getBean(PersonService.class);
            Person person = new Person();
            person.setName("Vitalii Ivanov");
            person.setAge(37);
            personService.save(person);

            personService.findAll().forEach(System.out::println);


            ClientService clientService = annotationConfigApplicationContext.getBean(ClientService.class);
            System.out.println(clientService.getClientName());
        }
    }
}
