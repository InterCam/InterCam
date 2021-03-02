package com.example.intercam.dto;

import lombok.Data;
import com.example.intercam.entity.FAQ;

@Data
public class FAQResponseDto {

    private Long id;
    private String title;
    private String contents;

    public FAQResponseDto(FAQ faq) {
        this.id = faq.getFaq_id();
        this.title = faq.getTitle();
        this.contents = faq.getContents();
    }
}
