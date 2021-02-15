package com.example.intercam.dto;

import lombok.Data;
import com.example.intercam.entity.faq;

@Data
public class FAQResponseDto {

    private int faq_id;
    private String title;
    private String contents;

    public FAQResponseDto(faq faq) {
        this.faq_id = faq.getFaq_id();
        this.title = faq.getTitle();
        this.contents = faq.getContents();
    }
}
