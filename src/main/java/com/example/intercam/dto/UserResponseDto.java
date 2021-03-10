package com.example.intercam.dto;

import com.example.intercam.entity.Auth;
import com.example.intercam.entity.User;
import lombok.Data;

@Data
public class UserResponseDto {

    private String username;
    private String password;
    private Auth auth;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.auth = user.getAuth();
    }
}
