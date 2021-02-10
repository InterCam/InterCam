package com.example.intercam.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public Files(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
