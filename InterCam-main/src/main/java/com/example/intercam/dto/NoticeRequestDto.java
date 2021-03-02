package com.example.intercam.dto;

import com.example.intercam.entity.Notice;
import lombok.Builder;
import lombok.Data;

@Data
public class NoticeRequestDto {
    private String title;
    private String content;

    @Builder
    public NoticeRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Notice toEntity(){
        return Notice.builder().title(title).content(content).build();
    }
}
