package com.colvir.webinar14.controller;

import com.colvir.webinar10.service.DepartmentEmployeeService;
import com.colvir.webinar14.SpringBootAppTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false) //альтернативный способ отключать spring security
//@AutoConfigureMockMvc
@DisplayName("Контроллер для работы с департаментами должен ")
public class DepartmentControllerTest extends SpringBootAppTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentEmployeeService departmentEmployeeService;

    @Test
    @DisplayName("уметь получать информацию о сотрудниках департамента по идентификатору")
//    @WithMockUser(username = "admin", roles = {"GUEST"})
    public void shouldAbleToGetDepartmentById() throws Exception {
        this.mockMvc.perform(get("/api/departments/1/employees"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
