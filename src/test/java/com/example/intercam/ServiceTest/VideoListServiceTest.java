package com.example.intercam.ServiceTest;

import com.example.intercam.Repository.*;
import com.example.intercam.entity.Comment;
import com.example.intercam.entity.VideoList;
import com.example.intercam.service.VideoListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class VideoListServiceTest {
    @Autowired
    private VideoListService videoListService;

    @Autowired
    private VideoListRepository videoListRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnalysisRepository analysisRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void before(){

        Comment comment = Comment.builder().contents("contents").score(3).build();

        VideoList videoList = videoListRepository.findById(2L).get();

        videoList.addComment(comment);

        commentRepository.save(comment);
    }

    @Test
    public void getAllList(){
        VideoList videoList = videoListRepository.findById(2L).get();

        System.out.println(videoList.getCommentList().get(0).getContents());
    }

}
