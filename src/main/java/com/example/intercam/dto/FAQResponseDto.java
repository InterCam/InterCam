package com.example.intercam.dto;

import lombok.Data;
import com.example.intercam.entity.FAQ;

@Data
public class FAQResponseDto {

    private Long faq_id;
    private String title;
    private String contents;

    public FAQResponseDto(FAQ faq) {
        this.faq_id = faq.getFaq_id();
        this.title = faq.getTitle();
        this.contents = faq.getContents();
    }
}
