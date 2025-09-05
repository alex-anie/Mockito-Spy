package com.alexanie.app.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int numberOfEmployees;

    @ElementCollection
    private List<String> industries; // tech-related industries

    // Default constructor required by JPA
    public Companies() {}

    // Constructor with fields
    public Companies(Long id, String name, int numberOfEmployees, List<String> industries) {
        this.id = id;
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.industries = industries;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getNumberOfEmployees() { return numberOfEmployees; }
    public void setNumberOfEmployees(int numberOfEmployees) { this.numberOfEmployees = numberOfEmployees; }

    public List<String> getIndustries() { return industries; }
    public void setIndustries(List<String> industries) { this.industries = industries; }
}
