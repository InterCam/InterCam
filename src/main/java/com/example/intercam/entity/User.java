package com.example.intercam.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    private String name;
    private String birth;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String username, String password, String name, String birth, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.role = Role.USER;
    }

    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.USER;
    }

    public void changeRole(Role role) {
        this.role = role;
    }

    public void changePassword(String password){
        this.password = password;
    }
}
