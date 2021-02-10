package com.example.intercam.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FAQ_repository extends JpaRepository<faq, Integer> {
    List<faq> findAll();
    Optional<faq> findById(Integer num);
}
