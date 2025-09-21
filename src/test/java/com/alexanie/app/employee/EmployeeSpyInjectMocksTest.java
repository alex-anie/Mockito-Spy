package com.alexanie.app.employee;

import com.alexanie.app.model.Employee;
import com.alexanie.app.service.EmployeeService;
import com.alexanie.app.controller.EmployeeController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeSpyInjectMocksTest {

    @Spy
    private EmployeeService employeeService = new EmployeeService(null); // spy real service

    @InjectMocks
    private EmployeeController employeeController; // controller with service injected

    @Test
    @DisplayName("Find profession with highest salary using @Spy + @InjectMocks")
    void findTopEarningProfession() {
        // Fake dataset
        List<Employee> fakeEmployees = Arrays.asList(
                new Employee(1L, "Alice Johnson", "Backend Developer", 95000.0,
                        Arrays.asList("Java", "Spring Boot", "SQL")),
                new Employee(2L, "Bob Smith", "Frontend Developer", 87000.0,
                        Arrays.asList("JavaScript", "React", "CSS")),
                new Employee(3L, "David Kim", "Data Scientist", 115000.0,
                        Arrays.asList("Python", "TensorFlow", "Pandas"))
        );

        // Stub the spy service directly
        doReturn(fakeEmployees).when(employeeService).getAllEmployees();

        // Use controller method
        List<Employee> employees = employeeController.getAllEmployees();

        // Find highest earner
        Employee topEarner = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow();

        // Assertions
        assertThat(topEarner.getProfession()).isEqualTo("Data Scientist");
        assertThat(topEarner.getSalary()).isEqualTo(115000.0);

        // Verify service interaction
        verify(employeeService).getAllEmployees();
    }
}
