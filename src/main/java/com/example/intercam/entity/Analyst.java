package com.example.intercam.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@DiscriminatorValue("ANALYST")
@Entity
@Setter
public class Analyst extends User{
    private String contents;
    private String img;

    @Builder
    public Analyst(@NotNull String username, @NotNull String password, @NotNull String phone, @NotNull String name,
                   @NotNull String birth, String contents, String img) {
        super(username, password, phone, name, birth);
        this.contents = contents;
        this.img = img;
    }
}
