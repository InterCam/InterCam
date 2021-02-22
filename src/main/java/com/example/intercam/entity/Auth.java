package com.example.intercam.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//변경시간 & 생성시간필요
public enum Auth {
    USER("ROLE_USER","유저"),
    ANALYST("ROLE_ANALYST","평가자"),
    ADMIN("ROLE_ADMIN","관리자");

    private final String key;
    private final String title;
}