package com.alexanie.app.exceptions;

import com.alexanie.app.model.Companies;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class CompaniesSpyExceptionTest {

    @Test
    @DisplayName("Spy with doThrow() for exception handling")
    void spyWithDoThrow() {
        // Create a real object
        Companies realCompany = new Companies(
                3L,
                "CloudX",
                500,
                java.util.Arrays.asList("Cloud", "SaaS")
        );

        // Spy the object
        Companies spyCompany = spy(realCompany);

        // Stub a void method to throw an exception
        doThrow(new IllegalStateException("Database connection failed"))
                .when(spyCompany)
                .clearDepartments(); // assume this is a void method in Companies

        // Act & Assert: verify that exception is thrown
        assertThatThrownBy(spyCompany::clearDepartments)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Database connection failed");

        // Verify interaction
        verify(spyCompany).clearDepartments();

        System.out.println("Exception successfully stubbed with doThrow()");
    }
}
