package com.alexanie.app.controller;

import com.alexanie.app.model.Companies;
import com.alexanie.app.service.CompaniesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompaniesController {

    private final CompaniesService service;

    public CompaniesController(CompaniesService service) {
        this.service = service;
    }

    // GET all companies
    @GetMapping
    public List<Companies> getAllCompanies() {
        return service.getAllCompanies();
    }

    // GET company by id
    @GetMapping("/{id}")
    public ResponseEntity<Companies> getCompanyById(@PathVariable Long id) {
        return service.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new company
    @PostMapping
    public Companies addCompany(@RequestBody Companies company) {
        return service.addCompany(company);
    }

    // PUT update company
    @PutMapping("/{id}")
    public ResponseEntity<Companies> updateCompany(@PathVariable Long id, @RequestBody Companies companyDetails) {
        try {
            return ResponseEntity.ok(service.updateCompany(id, companyDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE company
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        service.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
