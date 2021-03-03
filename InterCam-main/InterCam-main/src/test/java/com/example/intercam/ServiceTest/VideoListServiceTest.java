package com.example.intercam.ServiceTest;

import com.example.intercam.Repository.UserRepository;
import com.example.intercam.Repository.VideoListRepository;
import com.example.intercam.Repository.VideoRepository;
import com.example.intercam.entity.Auth;
import com.example.intercam.entity.User;
import com.example.intercam.entity.Video;
import com.example.intercam.entity.VideoList;
import com.example.intercam.service.VideoListService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//    @BeforeAll
//    public void saveUser(){
//        User u = new User();
//        u.setAuth(Auth.USER);
//        u.setBirth("2");
//        u.setName("aaaaa");
//        u.setPassword("a");
//        u.setPhone("010");
//        u.setUsername("11asdasd1");
//
//        userRepository.save(u);
//    }


//    @BeforeAll
//    public void saveList(){
//        Video v = Video.builder()
//                .title("test")
//                .url("/WEB-INF/test.mov")
//                .build();
//
//        videoRepository.save(v);
//
//        Optional <Video> v1 = videoRepository.findById(1L);
//        Optional<User> u1 = userRepository.findById(1L);
//
//        VideoList vl1 = VideoList.builder()
//                .major("aaa")
//                .views(111)
//                .avgScore(98.0f)
//                .build();
//
//        VideoList vl2 = VideoList.builder()
//                .major("aaa")
//                .views(20)
//                .avgScore(10.0f)
//                .build();
//
//        VideoList vl3 = VideoList.builder()
//                .major("aaa")
//                .views(13)
//                .avgScore(51.9f)
//                .build();
//
//        VideoList vl4 = VideoList.builder()
//                .major("aaa")
//                .views(48)
//                .avgScore(73.5f)
//                .build();
//
//        VideoList vl5 = VideoList.builder()
//                .major("aaa")
//                .views(500)
//                .avgScore(56.9f)
//                .build();
//
//        vl1.addVideo(v);
//        vl1.addUser(u1.get());
//
//        vl2.addVideo(v1.get());
//        vl2.addUser(u1.get());
//
//        vl3.addVideo(v1.get());
//        vl3.addUser(u1.get());
//
//        vl4.addVideo(v1.get());
//        vl4.addUser(u1.get());
//
//        vl5.addVideo(v1.get());
//        vl5.addUser(u1.get());
//
//        videoListService.save(vl1);
//        videoListService.save(vl2);
//        videoListService.save(vl3);
//        videoListService.save(vl4);
//        videoListService.save(vl5);
//    }

    @Test
    public void getAllList(){
//        Page<VideoList> page2 =videoListRepository.findAll(PageRequest.of(0, 10,  Sort.by(Sort.Direction.DESC, "listId")));
//        System.out.println(page2.get());
    Optional<VideoList> test = videoListRepository.findById(1L);
        System.out.println(test.get().getUser_id().getName());

    }

}
