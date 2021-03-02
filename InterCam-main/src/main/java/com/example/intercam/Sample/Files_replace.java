package com.example.intercam.Sample;

import lombok.Builder;

import javax.persistence.*;

@Entity
public class Files_replace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public Files_replace(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
