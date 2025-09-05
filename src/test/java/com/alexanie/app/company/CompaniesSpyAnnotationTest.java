package com.alexanie.app.company;

import com.alexanie.app.model.Companies;
import com.alexanie.app.service.CompaniesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CompaniesSpyAnnotationTest {

    @Spy
    private CompaniesService companiesService = new CompaniesService(null); // real object, repo null for demo

    @Test
    @DisplayName("Find company by industry using @Spy")
    void spyCompaniesService_findByIndustry() {
        // Fake companies dataset
        List<Companies> fakeCompanies = Arrays.asList(
                new Companies(1L, "TechNova", 1200, Arrays.asList("AI", "Cloud")),
                new Companies(2L, "CodeSmiths", 500, Arrays.asList("Software Development", "DevOps")),
                new Companies(3L, "CyberSecPro", 300, Arrays.asList("Cybersecurity", "Networking"))
        );

        // Stub only getAllCompanies() on the spy
        doReturn(fakeCompanies).when(companiesService).getAllCompanies();

        // Find company that has "AI" as industry
        Companies aiCompany = companiesService.getAllCompanies().stream()
                .filter(c -> c.getIndustries().contains("AI"))
                .findFirst()
                .orElseThrow();

        // Assertions
        assertThat(aiCompany.getName()).isEqualTo("TechNova");
        assertThat(aiCompany.getIndustries()).contains("AI");

        // Verify spy interaction
        verify(companiesService).getAllCompanies();
    }
}
