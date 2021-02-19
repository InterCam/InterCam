package com.example.intercam.dto;

import com.example.intercam.Sample.Files_replace;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilesSaveRequestDto {

    private String fileName;
    private String filePath;

    @Builder
    public FilesSaveRequestDto(String filename, String filePath) {
        this.fileName = filename;
        this.filePath = filePath;
    }

    public Files_replace toEntity(){
        return Files_replace.builder().fileName(fileName).filePath(filePath).build();
    }
}
