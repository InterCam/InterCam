package com.example.intercam.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeResponseDto {

    private String username;
    private String name;
    private String phone;

    @Builder
    public ChangeResponseDto(String username, String name, String phone) {
        this.username = username;
        this.name = name;
        this.phone = phone;
    }
}
