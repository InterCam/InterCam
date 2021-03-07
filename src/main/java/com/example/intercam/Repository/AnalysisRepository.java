package com.example.intercam.Repository;

import com.example.intercam.entity.Analyst;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRepository extends JpaRepository<Analyst, Long> {
    Analyst findByUsername(String username);
}
