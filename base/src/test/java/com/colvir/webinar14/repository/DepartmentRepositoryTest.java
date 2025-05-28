package com.colvir.webinar14.repository;

import com.colvir.webinar10.model.Department;
import com.colvir.webinar10.repository.DepartmentRepository;
import com.colvir.webinar14.SpringBootAppTest;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@SpringBootTest
@DisplayName("DAO для работы с департаментами на основе JPA должен ")
@Transactional
@DirtiesContext(classMode = BEFORE_CLASS)
public class DepartmentRepositoryTest extends SpringBootAppTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @DisplayName("уметь получать список всех департаментов")
    @Test
    public void shouldReturnCorrectAllDepartmentList() {
        val departments = departmentRepository.findAll();
        assertThat(departments).isNotNull().hasSize(4)
                .allMatch(not(s -> s.getId() == null))
                .allMatch(not(s -> s.getName().isEmpty()));
        departments.forEach(System.out::println);
    }

    @DisplayName("уметь загружать информацию о конкретном департаменте по его идентификатору")
    @Test
    public void shouldFindExpectedDepartmentById(){
        Long departmentId = 1L;
        val actualDepartment = departmentRepository.findById(departmentId).orElse(null);
        assertThat(actualDepartment).isNotNull()
                .hasFieldOrPropertyWithValue("name", "Development");
    }

    @DisplayName("уметь создавать департаменты, а потом загружать информацию о нем")
    @Test
    public void shouldSaveAndLoadCorrectDepartment() {
        val expectedDepartment = new Department();
        expectedDepartment.setName("DevOps");
        val actualDepartment = departmentRepository.save(expectedDepartment);
        assertThat(actualDepartment).isEqualTo(expectedDepartment);
    }

    @DisplayName("уметь удалять департамент")
    @Test
    public void shouldDeleteDepartment() {
        val DepartmentCountBefore = departmentRepository.findAll().size();
        val newDepartment = new Department();
        newDepartment.setName("Not existing");
        val Department = departmentRepository.save(newDepartment);
        departmentRepository.delete(Department);
        val DepartmentCountAfter = departmentRepository.findAll().size();

        assertThat(DepartmentCountBefore).isEqualTo(DepartmentCountAfter);
    }
}
