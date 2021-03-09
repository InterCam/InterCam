package com.example.intercam.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="list_id") // 유저 리스트
    private VideoList list_Id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id") // 유저 리스트
    private Analyst analyst_id;

    @Lob @NotNull // 내용
    private String contents;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int score;

    @Builder
    public Comment(@NotNull String contents, int score) {
        this.contents = contents;
        this.score = score;
    }

    public void addAnalyst(Analyst analyst){
        this.analyst_id = analyst;
    }

    public void addVideoList(VideoList videoList) {
        this.list_Id = videoList;
    }
}
