package com.api.portfolio.repository;

import com.api.portfolio.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExpRepository extends JpaRepository<WorkExperience, Integer> {
    
}
