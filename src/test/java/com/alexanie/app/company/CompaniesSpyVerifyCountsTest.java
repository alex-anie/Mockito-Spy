package com.alexanie.app.company;

import com.alexanie.app.model.Companies;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CompaniesSpyVerifyCountsTest {

    @Test
    @DisplayName("Verify method call counts with spy")
    void verifyMethodCallCounts() {
        // Create a real Companies object
        Companies realCompany = new Companies(
                20L,
                "CloudWare",
                2500,
                Arrays.asList("Cloud", "AI", "DevOps")
        );

        // Wrap the object with a spy
        Companies spyCompany = spy(realCompany);

        // Stub one method for demonstration
        when(spyCompany.getNumberOfEmployees()).thenReturn(4000);

        // Interact with the spy
        String name = spyCompany.getName();
        int employees = spyCompany.getNumberOfEmployees();
        int employeesAgain = spyCompany.getNumberOfEmployees();

        // Assertions
        assertThat(name).isEqualTo("CloudWare");
        assertThat(employees).isEqualTo(4000);       // Stubbed value
        assertThat(employeesAgain).isEqualTo(4000);  // Stubbed value

        // Verify call counts
        verify(spyCompany, times(1)).getName();                 // called once
        verify(spyCompany, times(2)).getNumberOfEmployees();    // called twice
        verify(spyCompany, never()).setName("SomethingElse");   // never called
        verify(spyCompany, atLeast(1)).getNumberOfEmployees();  // at least once
        verify(spyCompany, atMost(3)).getNumberOfEmployees();   // no more than 3 times
    }
}
