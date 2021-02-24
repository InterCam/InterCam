package com.example.intercam.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faq_id;

    @NotNull // 제목
    private String title;

    @NotNull // 내용
    private String contents;

    @Builder
    public FAQ(@NotNull String title, @NotNull String contents) {
        this.title = title;
        this.contents = contents;
    }
}