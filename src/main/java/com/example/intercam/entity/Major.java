package com.example.intercam.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long major_Id;

    @Column(columnDefinition = "varchar(20)")
    private String name1;
    @Column(columnDefinition = "varchar(20)")
    private String name2;
    @Column(columnDefinition = "varchar(20)")
    private String name3;

    @Builder
    public Major(String name1, String name2, String name3) {
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
    }
}
