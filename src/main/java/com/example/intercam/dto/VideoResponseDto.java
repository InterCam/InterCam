package com.example.intercam.dto;

import com.example.intercam.entity.VideoList;
import lombok.Data;

@Data
public class VideoResponseDto {

    private Long id;
    private String title;
    private Float avgScore;
    private String url;
    private String username;
    private String graph;

    public VideoResponseDto(VideoList videoList) {
        this.id = videoList.getListId();
        this.title = videoList.getVideo_id().getTitle();
        this.avgScore = videoList.getAvgScore();
        this.url = videoList.getVideo_id().getUrl();
        this.username = videoList.getUserId().getUsername();
        this.graph = videoList.getVideo_id().getGraph();
    }
}