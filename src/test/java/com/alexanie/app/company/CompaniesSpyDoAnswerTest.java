package com.alexanie.app.company;

import com.alexanie.app.model.Companies;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CompaniesSpyDoAnswerTest {

    @Test
    @DisplayName("Spy with doAnswer() for dynamic behavior")
    void spyWithDoAnswer() {
        // Real object
        Companies realCompany = new Companies(
                1L,
                "TechNova",
                1200,
                Arrays.asList("AI", "Cloud", "Data")
        );

        // Spy the object
        Companies spyCompany = spy(realCompany);

        // Stub getNumberOfEmployees() dynamically with doAnswer()
        doAnswer(invocation -> {
            // Access the real method value
            int original = realCompany.getNumberOfEmployees();
            // Add 100 as a "bonus headcount"
            return original + 100;
        }).when(spyCompany).getNumberOfEmployees();

        // Act
        int employees = spyCompany.getNumberOfEmployees();

        // Assert
        assertThat(employees).isEqualTo(1300); // 1200 original + 100 bonus
        assertThat(spyCompany.getName()).isEqualTo("TechNova"); // Real value works

        // Verify interaction
        verify(spyCompany).getNumberOfEmployees();

        System.out.println("Dynamic employees count: " + employees);
        // -> Dynamic employees count: 1300
    }
}
