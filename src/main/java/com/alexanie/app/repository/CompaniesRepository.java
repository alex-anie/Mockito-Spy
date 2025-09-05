package com.alexanie.app.repository;

import com.alexanie.app.model.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Long> {
}
