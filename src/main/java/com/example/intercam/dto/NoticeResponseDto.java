package com.example.intercam.dto;

import com.example.intercam.entity.Notice;
import lombok.Data;

@Data
public class NoticeResponseDto {

    private Long id;
    private String title;
    private String content;

    public NoticeResponseDto(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }
}
