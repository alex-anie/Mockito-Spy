package com.alexanie.app.employee;

import com.alexanie.app.model.Employee;
import com.alexanie.app.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerIntegrationSpyTest {

    @Autowired
    private MockMvc mockMvc;

    // Spy on the actual EmployeeService bean
    @MockitoSpyBean
    private EmployeeService employeeService;

    @Test
    @DisplayName("GET /api/employees - should return all employees (spied service)")
    void getAllEmployees_withSpy() throws Exception {
        // Stub the spy to return controlled data
        doReturn(List.of(
                new Employee(1L, "Alice", "Backend Dev", 95000.0, List.of("Java", "Spring Boot")),
                new Employee(2L, "Bob", "Frontend Dev", 87000.0, List.of("React", "CSS"))
        )).when(employeeService).getAllEmployees();

        // Perform GET request
        mockMvc.perform(get("/api/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"));

        // Verify interaction with spy
        verify(employeeService).getAllEmployees();
    }
}
