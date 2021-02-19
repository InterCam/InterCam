package com.example.intercam.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class VideoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long list_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id") // 유저
    private User user_id;

    private Float avg_score; // 평균점수

    @NotNull // 전공
    private String major;

    private String education; // 학력

    private String experience; // 경력

    @NotNull
    private Boolean status; // 상태값(사용, 미사용)

    //TODO 후순위. 로직 구현 필요
    private int views; // 조회수

    @OneToMany (fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Comment> commentList;

    @OneToOne // 동영상
    private Video video_id;

    @Builder
    public VideoList( @NotNull String major, String education, String experience,
                      @NotNull Boolean status, int views) {
        this.major = major;
        this.education = education;
        this.experience = experience;
        this.status = status;
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
