package com.example.intercam.Sample;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository_replace extends JpaRepository<Files_replace, Long> {

    Optional<Files_replace> findByFileName(String fileName);

}
