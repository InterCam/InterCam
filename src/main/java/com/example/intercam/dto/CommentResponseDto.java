package com.example.intercam.dto;

import com.example.intercam.entity.Comment;
import lombok.Data;

@Data
public class CommentResponseDto {

    private String name;
    private Long commentId;
    private String contents;
    private Integer score;

    public CommentResponseDto(Comment comment) {
        this.name = comment.getAnalyst_id().getName();
        this.commentId = comment.getComment_id();
        this.contents = comment.getContents();
        this.score = comment.getScore();
    }
}
