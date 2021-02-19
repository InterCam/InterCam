package com.example.intercam.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@DiscriminatorValue("ANALYST")
@Entity
public class Analyst extends User{
    private String img; // 이미지 url

    @NotNull
    private String contents; // 이력 / 학력

    @Builder
    public Analyst(@NotNull String username, @NotNull String password, @NotNull String phone, @NotNull String name,
                   @NotNull String birth, String img, @NotNull String contents) {
        super(username, password, phone, name, birth);
        this.img = img;
        this.contents = contents;
    }
}
