package com.alexanie.app.company;

import com.alexanie.app.model.Companies;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CompaniesSpyObjectTest {

    @Test
    @DisplayName("Spy on Companies object and override getter")
    void spyOnCompaniesObject() {
        // Create a real Companies instance
        Companies realCompany = new Companies(
                10L,
                "NextGenAI",
                1500,
                Arrays.asList("AI", "Robotics", "Cloud")
        );

        // Wrap it in a spy
        Companies spyCompany = spy(realCompany);

        // Stub one method: override getNumberOfEmployees()
        when(spyCompany.getNumberOfEmployees()).thenReturn(3000);

        // Use spy normally
        String name = spyCompany.getName();
        int employees = spyCompany.getNumberOfEmployees(); // <- overridden

        // Assertions
        assertThat(name).isEqualTo("NextGenAI");   // comes from real object
        assertThat(employees).isEqualTo(3000);     // comes from stubbed spy

        // Verify interactions
        verify(spyCompany).getName();
        verify(spyCompany).getNumberOfEmployees();
    }
}
