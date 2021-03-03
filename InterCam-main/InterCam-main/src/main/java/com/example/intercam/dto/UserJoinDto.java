package com.example.intercam.dto;

import com.example.intercam.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserJoinDto {
    private String username;
    private String password;
    private String name;
    private String birth;
    private String phone;

    @Builder
    public UserJoinDto(String username, String password, String name, String birth, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
    }

    public User toEntity(){
        return new User(this);
    }
}
