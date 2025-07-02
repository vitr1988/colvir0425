package com.colvir.webinar26.repository;

import com.colvir.webinar26.model.Department;
import com.colvir.webinar26.repostiory.DepartmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Репозиторий для работы с департаментами на реактивном подходе должен ")
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;

    @Test
    @DisplayName("уметь сохранять департаменты")
    public void shouldSaveNewDepartment() {
        Mono<Department> departmentMono = repository.save(new Department("1", "IT"));

        StepVerifier
                .create(departmentMono)
                .assertNext(department -> assertThat(department.getId()).isNotEmpty())
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("уметь сохранять департамент и получать информацию по идентификатору")
    public void shouldSaveAndFindById() {
        repository.save(new Department("1", "IT"))
                .subscribe(System.out::println);

        StepVerifier.create(repository.findById("1"))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }
}
