package com.alexanie.app.service;

import com.alexanie.app.model.Companies;
import com.alexanie.app.repository.CompaniesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesService {

    private final CompaniesRepository repository;

    public CompaniesService(CompaniesRepository repository) {
        this.repository = repository;
    }

    public List<Companies> getAllCompanies() {
        return repository.findAll();
    }

    public Optional<Companies> getCompanyById(Long id) {
        return repository.findById(id);
    }

    public Companies addCompany(Companies company) {
        return repository.save(company);
    }

    public Companies updateCompany(Long id, Companies companyDetails) {
        return repository.findById(id)
                .map(company -> {
                    company.setName(companyDetails.getName());
                    company.setNumberOfEmployees(companyDetails.getNumberOfEmployees());
                    company.setIndustries(companyDetails.getIndustries());
                    return repository.save(company);
                })
                .orElseThrow(() -> new RuntimeException("Company not found with id " + id));
    }

    public void deleteCompany(Long id) {
        repository.deleteById(id);
    }
}
