package com.example.intercam.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AskRequestDto {

    private String title;
    private String content;

    @Builder
    public AskRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
