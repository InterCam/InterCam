package com.example.intercam.entity;

import lombok.*;

import javax.persistence.*;
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

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH}) // User - VideoList 저장 시 persist Error 발생함. persist 는 빼야함
    @JoinColumn(name="userId") // 유저
    private User userId;

    private Float avgScore; // 평균점수 Paging 시 '_'를 못가져와서 이름 변경

    @OneToMany (mappedBy = "listId",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Comment> commentList;

    @OneToOne // 동영상
    @JoinColumn(name = "video_id")
    private Video video_id;

    public void addVideo(Video video){
        this.video_id = video;
        video.addVideoList(this);
    }

    public void addComment(Comment comment){
        if(commentList==null){
            commentList = new ArrayList<>();
        }
        commentList.add(comment);
        comment.addVideoList(this);
    }

    // FINISH
    public void addUser(User user){
        this.userId = user;
    }
}
