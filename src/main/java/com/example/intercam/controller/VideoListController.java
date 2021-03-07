package com.example.intercam.controller;

import com.example.intercam.Repository.VideoListRepository;
import com.example.intercam.entity.VideoList;
import com.example.intercam.service.VideoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class VideoListController {
    private final VideoListService videoListService;

    private final VideoListRepository videoListRepository;

    @GetMapping("/list/videoList")
    public String videoList(Model model,
                            @RequestParam(value = "page", defaultValue = "1") Integer pageNum){
        List<VideoList> videoLists = videoListService.getVideoList(pageNum);
        Integer[] pageList = videoListService.getPageList(pageNum);

        model.addAttribute("videoLists", videoLists);
        model.addAttribute("pageList", pageList);
        return "/alllist/videoList_idDESC";
    }

}