package com.example.intercam.controller;

import com.example.intercam.dto.CommentResponseDto;
import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.dto.VideoResponseDto;
import com.example.intercam.entity.Auth;
import com.example.intercam.service.CommentService;
import com.example.intercam.service.VideoListService;
import com.example.intercam.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class VideoListController {
    private final VideoListService videoListService;
    private final CommentService commentService;
    private final VideoService videoService;

    @GetMapping("/list/videoList")
    public String videoList(Model model, String page){
        List<VideoResponseDto> videoLists = videoListService.getVideoList(page);
        model.addAttribute("videoLists", videoLists);

        return "alllist/videoList_idDESC";
    }

    @GetMapping("/list/videoRankList")
    public String videoRankList(Model model){
        List<VideoResponseDto> videoResponseDtos = videoListService.getVideoRankList();

        model.addAttribute("videoLists", videoResponseDtos);

        return "alllist/videoRankList";
    }

    @GetMapping("/list/detail")
    public String detailList(Long id , Model model, @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto") UserResponseDto userResponseDto){

        videoService.checkfile(userResponseDto);

        VideoResponseDto videoResponseDto = videoListService.avgScore(id);
        List<CommentResponseDto> commentResponseDtos = commentService.findComments(id);


        model.addAttribute("videoList", videoResponseDto);
        model.addAttribute("commentList",commentResponseDtos);

        model.addAttribute("graph",videoResponseDto.getGraph());

        if(userResponseDto == null || userResponseDto.getAuth() == Auth.USER){
            model.addAttribute("error", "login.please");
            return "File/detail";
        }
        return "File/detail";
    }

}