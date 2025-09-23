package com.alexanie.app.employee;

import com.alexanie.app.model.Employee;
import com.alexanie.app.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class EmployeeServiceMockVsSpyTest {

    @Test
    @DisplayName("Using Mock: Behavior Driven by Stubbing Only")
    void testWithMock() {
        // Create a MOCK
        EmployeeService mockService = mock(EmployeeService.class);

        // Stub the behavior (fake data)
        when(mockService.getAllEmployees()).thenReturn(
                List.of(
                        new Employee(1L, "Alice", "Backend Dev", 95000.0, List.of("Java")),
                        new Employee(2L, "Bob", "Frontend Dev", 87000.0, List.of("React"))
                )
        );

        // Act
        List<Employee> employees = mockService.getAllEmployees();

        // Assert
        assertThat(employees).hasSize(2);
        assertThat(employees.get(0).getName()).isEqualTo("Alice");

        // Verify interaction
        verify(mockService).getAllEmployees();
    }

    @Test
    @DisplayName("Using Spy: Real Methods + Selective Stubbing")
    void testWithSpy() {
        // Create a real service (with dummy data inside)
        EmployeeService realService = new EmployeeService(null);

        // Create a SPY wrapping the real service
        EmployeeService spyService = spy(realService);

        // Optionally stub one method (if needed)
        doReturn(List.of(
                new Employee(99L, "James", "DevOps", 100000.0, List.of("Docker"))
        )).when(spyService).getAllEmployees();

        // Act
        List<Employee> employees = spyService.getAllEmployees();

        // Assert
        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getName()).isEqualTo("James");

        // Verify interaction
        verify(spyService).getAllEmployees();
    }
}
