package com.colvir.webinar13.repository;

import com.colvir.webinar13.model.Employee;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
//@Transactional
@DisplayName("Репозиторий для работы с сотрудниками должен ")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    @DisplayName("уметь сохранять сотрудников")
    public void shouldSaveNewEmployee() {
        val expectedEmployee = new Employee();
        expectedEmployee.setFirstName("Ivan");
        expectedEmployee.setLastName("Ivanov");
        val actualEmployee = repository.save(expectedEmployee);
        assertThat(actualEmployee).isEqualTo(expectedEmployee);
    }
}
