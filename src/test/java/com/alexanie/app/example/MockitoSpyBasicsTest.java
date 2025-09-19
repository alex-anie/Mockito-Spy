package com.alexanie.app.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MockitoSpyBasicsTest {

    @Test
    void spyOnArrayList() {
        // Create a real object and wrap it in a spy
        List<String> employeeNames = Mockito.spy(new ArrayList<>());

        // Add employee to the list
        employeeNames.add("Alice Johnson");
        employeeNames.add("Bob Smith");
        employeeNames.add("John Okafor");
        employeeNames.add("Chisom Anie");

        // verify employee on list
        verify(employeeNames).add("Alice Johnson");
        verify(employeeNames).add("Bob Smith");
        verify(employeeNames).add("John Okafor");
        verify(employeeNames).add("Chisom Anie");

        // Stub a method
        when(employeeNames.get(0)).thenReturn("Alex Anie");

        // Confirm if employee is on the list
      assertThat(employeeNames.get(0)).isEqualTo("Alex Anie");
      assertThat(employeeNames.get(3)).isEqualTo("Chisom Anie");
    }

}
