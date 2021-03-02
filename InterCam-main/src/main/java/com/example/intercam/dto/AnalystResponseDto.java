package com.example.intercam.dto;

import com.example.intercam.entity.Analyst;
import lombok.Data;

@Data
public class AnalystResponseDto {

    private String img;
    private String contents;
    private String name;

    public AnalystResponseDto(Analyst analyst) {
        this.img = analyst.getImg();
        this.contents = analyst.getContents();
        this.name = analyst.getName();
    }

}
