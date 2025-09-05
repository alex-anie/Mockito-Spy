package com.alexanie.app.company;

import com.alexanie.app.model.Companies;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CompaniesSpyStubbingTest {

    @Test
    @DisplayName("Spy with when().thenReturn()")
    void spyWithWhenThenReturn() {
        // Real object
        Companies realCompany = new Companies(
                1L,
                "TechWorld",
                500,
                Arrays.asList("Cloud", "AI", "Security")
        );

        // Spy the object
        Companies spyCompany = spy(realCompany);

        // Stubbing with when().thenReturn()
        when(spyCompany.getNumberOfEmployees()).thenReturn(1000);

        // Act
        int employees = spyCompany.getNumberOfEmployees();

        // Assert
        assertThat(employees).isEqualTo(1000);  // Stubbed value
        assertThat(spyCompany.getName()).isEqualTo("TechWorld"); // Real value still works

        // Verify interaction
        verify(spyCompany).getNumberOfEmployees();
    }

    @Test
    @DisplayName("Spy with doReturn().when()")
    void spyWithDoReturnWhen() {
        // Real object
        Companies realCompany = new Companies(
                2L,
                "DataHub",
                800,
                Arrays.asList("Big Data", "Analytics", "ML")
        );

        // Spy the object
        Companies spyCompany = spy(realCompany);

        // Stubbing with doReturn().when()
        doReturn(2000).when(spyCompany).getNumberOfEmployees();

        // Act
        int employees = spyCompany.getNumberOfEmployees();

        // Assert
        assertThat(employees).isEqualTo(2000);  // Stubbed value
        assertThat(spyCompany.getName()).isEqualTo("DataHub"); // Real value still works

        // Verify interaction
        verify(spyCompany).getNumberOfEmployees();
    }
}
