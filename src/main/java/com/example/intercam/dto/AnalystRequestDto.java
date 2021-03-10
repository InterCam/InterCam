package com.example.intercam.dto;

import com.example.intercam.entity.Analyst;
import lombok.Builder;
import lombok.Data;

@Data
public class AnalystRequestDto {

    private String username;
    private String password;
    private String phone;
    private String name;
    private String birth;
    private String contents;

    @Builder
    public AnalystRequestDto(String username, String password, String phone, String name, String birth, String contents) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.birth = birth;
        this.contents = contents;
    }

    public Analyst toEntity(){
        return Analyst.builder()
                .username(username)
                .password(password)
                .phone(phone)
                .name(name)
                .birth(birth)
                .contents(contents)
                .build();
    }



}
