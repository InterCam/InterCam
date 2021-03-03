package com.example.intercam.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity @Setter @Builder
@AllArgsConstructor
public class VideoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="list_id")
    private Long listId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH}) // User - VideoList 저장 시 persist Error 발생함. persist 는 빼야함
    @JoinColumn(name="user_id") // 유저
    private User user_id;

    private Float avgScore; // 평균점수 Paging 시 '_'를 못가져와서 이름 변경

    @NotNull // 전공
    private String major;

    //TODO 후순위. 로직 구현 필요
    @Column(columnDefinition = "int(11) default 0")
    private int views; // 조회수

    @OneToMany (fetch = FetchType.LAZY)
    private List<Comment> commentList;

    @OneToOne // 동영상
    @JoinColumn(name = "video_id")
    private Video video_id;

    @Builder
    public VideoList( @NotNull String major, String education, String experience,
                      @NotNull Boolean status, int views) {
        this.major = major;
        this.views = views;
    }

    public void addVideo(Video video){
        this.video_id = video;
        video.addVideoList(this);
    }

    public void addComment(Comment comment){
        if(commentList == null){
            commentList = new ArrayList<>();
        }
        commentList.add(comment);
        comment.addVideoList(this);
    }

    // FINISH
    public void addUser(User user){
        this.user_id = user;
    }
}
