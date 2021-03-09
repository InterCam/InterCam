package com.example.intercam.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.example.intercam.Repository.UserRepository;
import com.example.intercam.Repository.VideoListRepository;
import com.example.intercam.config.S3Service;
import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.entity.User;
import com.example.intercam.entity.Video;
import com.example.intercam.entity.VideoList;
import com.example.intercam.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VideoController {

    private final S3Service s3Service;
    private final VideoService videoService;
    private final AmazonS3 s3Client;
    private final UserRepository userRepository;
    private final VideoListRepository videoListRepository;

    @GetMapping("/upload")
    public String write(Model model) {
        return "Sample/upload";
    }

    //업로드
    @PostMapping("/upload")
    public String upload(Video video, MultipartFile file, VideoList videoList,
                         @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto")
                                 UserResponseDto userResponseDto) throws IOException {
        String url = s3Service.upload(file);
        video.setUrl(url);
        videoService.saveVideo(video);
        User user = userRepository.findByUsername(userResponseDto.getUsername());
        videoList.setUserId(user);
        videoList.setVideo_id(video);
        videoService.saveVideoList(videoList);
        return "redirect:/";
    }

    // 내비디오
    @GetMapping("/myvideo")
    public String myvideo(Model model, @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto")UserResponseDto userResponseDto) {
        User user = userRepository.findByUsername(userResponseDto.getUsername());
        List<VideoList> videoList =  videoService.getVideoList(user);
        List<String> url = null;
        for(VideoList list:videoList){
            url.add(list.getVideo_id().getUrl());
        }
        model.addAttribute("videoList", videoList);
        model.addAttribute("url",url);
        return "Sample/myvideo";
    }
}