package com.alexanie.app.extra;

import com.alexanie.app.model.Companies;
import com.alexanie.app.repository.CompaniesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class AppCompanies {


    @Bean
    CommandLineRunner initCompanies(CompaniesRepository repository) {
        return getCommandLineRunner(repository);
    }

    static CommandLineRunner getCommandLineRunner(CompaniesRepository repository) {
        return args -> {
            repository.saveAll(Arrays.asList(
                    new Companies(null, "TechNova", 1200, Arrays.asList("AI", "Cloud")),
                    new Companies(null, "CodeSmiths", 500, Arrays.asList("Software Development", "DevOps")),
                    new Companies(null, "DataWorks", 750, Arrays.asList("Big Data", "Machine Learning")),
                    new Companies(null, "CyberSecPro", 300, Arrays.asList("Cybersecurity", "Networking")),
                    new Companies(null, "AppMasters", 950, Arrays.asList("Mobile Apps", "Web Development"))
            ));
        };
    }
}
