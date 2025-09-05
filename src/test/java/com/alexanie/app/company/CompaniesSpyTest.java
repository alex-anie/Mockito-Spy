package com.alexanie.app.company;

import com.alexanie.app.model.Companies;
import com.alexanie.app.service.CompaniesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompaniesSpyTest {

    @Test
    @DisplayName("Spy on CompaniesService and find company with fewest employees")
    void spyCompaniesService_findMinEmployees() {
        // Create real service instance
        CompaniesService realService = Mockito.spy(new CompaniesService(null));

        // Stub the getAllCompanies() method with fake data
        List<Companies> fakeCompanies = Arrays.asList(
                new Companies(1L, "TechNova", 1200, Arrays.asList("AI", "Cloud")),
                new Companies(2L, "CodeSmiths", 500, Arrays.asList("Software Development", "DevOps")),
                new Companies(3L, "CyberSecPro", 300, Arrays.asList("Cybersecurity", "Networking"))
        );
        Mockito.doReturn(fakeCompanies).when(realService).getAllCompanies();

        // Use the spy to interact with realService methods
        List<Companies> companies = realService.getAllCompanies();

        // find the company with the smallest number of employees
        Companies minCompany = companies.stream()
                .min((c1, c2) -> Integer.compare(c1.getNumberOfEmployees(), c2.getNumberOfEmployees()))
                .orElseThrow();

        // Assert the result
        assertThat(minCompany.getName()).isEqualTo("CyberSecPro");
        assertThat(minCompany.getNumberOfEmployees()).isEqualTo(300);

        // Verify interaction
        Mockito.verify(realService).getAllCompanies();
    }
}
