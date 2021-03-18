package com.example.intercam.dto;

import com.example.intercam.entity.Analyst;
import lombok.Data;

@Data
public class AnalystResponseDto {

    private String contents;
    private String name;
    private String img;

    public AnalystResponseDto(Analyst analyst) {
        this.contents = analyst.getContents();
        this.name = analyst.getName();
        this.img = analyst.getImg();
    }

}
