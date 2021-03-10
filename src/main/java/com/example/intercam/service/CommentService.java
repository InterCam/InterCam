package com.example.intercam.service;

import com.example.intercam.Repository.AnalysisRepository;
import com.example.intercam.Repository.CommentRepository;
import com.example.intercam.Repository.VideoListRepository;
import com.example.intercam.dto.CommentResponseDto;
import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.entity.Analyst;
import com.example.intercam.entity.Comment;
import com.example.intercam.entity.VideoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.InvalidIsolationLevelException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final VideoListRepository videoListRepository;
    private final AnalysisRepository analysisRepository;

    @Transactional
    public List<CommentResponseDto> findComments(Long id) {
        VideoList videoList = videoListRepository.findById(id).orElseThrow(()->new InvalidIsolationLevelException("에러에러!"));

        List<Comment> comments = videoList.getCommentList();
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        for(Comment c:comments){
            commentResponseDtos.add(new CommentResponseDto(c));
        }
        return commentResponseDtos;
    }

    @Transactional
    public String save(Long id, UserResponseDto userResponseDto, Integer score, String comment) {
        VideoList videoList = videoListRepository.findById(id).get();
        Analyst analyst = analysisRepository.findByUsername(userResponseDto.getUsername());

        for(Comment c:videoList.getCommentList()){
            if(c.getAnalyst_id().getUsername().equals(userResponseDto.getUsername())){
                return "이미 등록하셨습니다!";
            }
        }

        Comment c = Comment.builder().score(score).contents(comment).build();
        c.addAnalyst(analyst);
        videoList.addComment(c);

        commentRepository.save(c);

        return "댓글이 등록되었습니다!";
    }

    @Transactional
    public String delete(Long comment_id, String id, UserResponseDto userResponseDto) {
        Comment comment = commentRepository.findById(comment_id).orElseThrow(()->new IllegalArgumentException("에러!"));

        if(!comment.getAnalyst_id().getUsername().equals(userResponseDto.getUsername())){
            return "글을 등록하신 회원이 아니군요!";
        }

        commentRepository.delete(comment);

        return "삭제 완료!";
    }
}

