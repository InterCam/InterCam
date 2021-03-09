package com.example.intercam.service;

import com.example.intercam.Repository.CommentRepository;
import com.example.intercam.Repository.VideoListRepository;
import com.example.intercam.entity.Analyst;
import com.example.intercam.entity.Comment;
import com.example.intercam.entity.VideoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final VideoListRepository videoListRepository;
    private final VideoListService videoListService;

    @Transactional
    public void addAna(Analyst analyst, Comment comment){
        comment.addAnalyst(analyst);
    }

    @Transactional
    public void save(Comment comment){
        commentRepository.save(comment);
    }

    @Transactional
    public void removeFromList(Long videoId, Comment comment){
        Optional<VideoList> videoList = videoListRepository.findById(videoId);

        List<Comment> comments = videoList.get().getCommentList();

        List<Comment> c = commentRepository.findAll();
        Comment cc = c.get(c.size()-1);


        for(int i = 0; i <= cc.getComment_id(); ++i){
            if(comments.get(i).getComment_id() == comment.getComment_id()){
                comments.set(i, null);
                return;
            }
        }

        comment.setList_Id(null);
        commentRepository.deleteById(comment.getComment_id());
    }

    public Comment find(Long commentId) {
        Optional<Comment> c = commentRepository.findById(commentId);
        return c.get();
    }
}

