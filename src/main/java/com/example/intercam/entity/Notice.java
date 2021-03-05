package com.example.intercam.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String content;
    @Column(columnDefinition = "int(11) default 0")
    private int views;

    @Builder
    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void plusViews(){
        views = views + 1;
    }
}

