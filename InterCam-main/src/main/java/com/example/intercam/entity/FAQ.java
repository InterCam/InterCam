package com.example.intercam.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faq_id;

    @NotNull // 제목
    @Column(columnDefinition = "varchar(20)")
    private String title;

    @NotNull // 내용
    private String contents;

    @Builder
    public FAQ(@NotNull String title, @NotNull String contents) {
        this.title = title;
        this.contents = contents;
    }
}