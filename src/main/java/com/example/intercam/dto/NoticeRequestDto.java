package com.example.intercam.dto;

import com.example.intercam.entity.Notice;
import com.example.intercam.entity.VideoList;
import lombok.Builder;
import lombok.Data;

@Data
public class NoticeRequestDto {
    private String title;
    private String content;
    private int views;
    private Long id;

    @Builder
    public NoticeRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoticeRequestDto(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.views = notice.getViews();
    }

    public Notice toEntity(){
        return Notice.builder().title(title).content(content).build();
    }
}
