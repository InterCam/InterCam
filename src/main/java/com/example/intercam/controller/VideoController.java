package com.example.intercam.controller;

import com.example.intercam.config.S3Service;
import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.dto.VideoResponseDto;
import com.example.intercam.entity.User;
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

    @GetMapping("/upload")
    public String write(Model model) {
        return "File/upload";
    }

    //업로드
    @PostMapping("/upload")
    public String upload(String title, MultipartFile file, @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:user") User user) throws IOException {

        if(!file.getOriginalFilename().endsWith(".mp4")){
            throw new IllegalArgumentException("재생할 수 없는 파일!");
        }

        String url = s3Service.upload(file);
        videoService.saveVideo(title, url, user);

        return "redirect:/upload";
    }

    // 내비디오`
    @GetMapping("/myvideo")
    public String myvideo(Model model, @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto") UserResponseDto userResponseDto) {

        List<VideoResponseDto> videoResponseDtos = videoService.findMyVideo(userResponseDto);

        model.addAttribute("videoList", videoResponseDtos);
        return "File/myvideo";
    }
}