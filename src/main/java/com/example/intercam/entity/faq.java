package com.example.intercam.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class faq {
    @Id @GeneratedValue
    private int faq_id;

    @NotNull
    private String title;

    @NotNull
    private String contents;
}