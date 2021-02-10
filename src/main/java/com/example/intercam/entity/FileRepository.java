package com.example.intercam.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<Files, Long> {

    Optional<Files> findByFileName(String fileName);

}
