package com.example.intercam.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_GUEST","사용자"),
    ANALYST("ROLE_ANALYST","평가자"),
    ADMIN("ROLE_ADMIN","관리자");

    private final String key;
    private final String title;

}
