package com.example.intercam.Repository;

import com.example.intercam.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FAQ_repository extends JpaRepository<FAQ, Long> {
    List<FAQ> findAll();
}
