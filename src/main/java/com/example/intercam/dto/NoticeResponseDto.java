package com.example.intercam.dto;

import com.example.intercam.entity.Notice;
import lombok.Data;

@Data
public class NoticeResponseDto {
    private Long id;
    private String title;
    private String content;
    private int views;

    public NoticeResponseDto(Notice notice){
        id = notice.getId();
        title = notice.getTitle();
        content = notice.getContent();
        views = notice.getViews();
    }
}
