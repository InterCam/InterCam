package com.example.intercam.controller;


import com.example.intercam.entity.*;
import com.example.intercam.service.AnalystService;
import com.example.intercam.service.CommentService;
import com.example.intercam.service.VideoListService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class VideoListController {
    private final VideoListService videoListService;
    private final AnalystService analystService;
    private final CommentService commentService;

    @GetMapping("/list/videoList")
    public String videoList(Model model,
                            @RequestParam(value = "page", defaultValue = "1") Integer pageNum){
        List<VideoList> videoLists = videoListService.getVideoList(pageNum);
        Integer[] pageList = videoListService.getPageList(pageNum);

        model.addAttribute("videoLists", videoLists);
        model.addAttribute("pageList", pageList);
        return "/alllist/videoList_idDESC";
    }

    @GetMapping("/list/videoRankList")
    public String videoRankList(Model model){
        List<VideoList> videoLists = videoListService.getVideoRankList();

        model.addAttribute("videoLists", videoLists);

        return "/alllist/videoRankList";
    }

    @GetMapping("/list/detail")
    public String detailList(Long id , Model model,
                             @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:user")
                                     User user){
        VideoList videoList = videoListService.findVideo(id);
        videoListService.avgScore(videoList);

        if(user == null || user.getAuth() == Auth.USER){
            // 로그인 안 한 유저가 쓰면 알림창.
            model.addAttribute("error", "login.please");
            model.addAttribute("videoList", videoList);
            return "/Sample/detail";
        }

        model.addAttribute("user", user);
        model.addAttribute("videoList", videoList);

        return "/Sample/detail";
    }


    // TODO 스코어 이쁘게..
    @PostMapping("/list/detail")
    @ResponseBody
    public String comment(@RequestParam String id,
                          @RequestParam String score,
                          @RequestParam String comment,
                          @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:user") User user,
                          Model model) {
        Long videoListId = Long.valueOf(id).longValue();
        Integer videoListScore = Integer.valueOf(score).intValue();

        if(user.getAuth() != Auth.ANALYST || user==null){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("result", false);
            model.addAttribute("error", "wrong.auth");
            return "/Sample/detail";
        }

        Analyst analyst = analystService.findAna(user.getUserId());

        Comment c = Comment.builder()
                .contents(comment)
                .score(videoListScore)
                .build();

        commentService.save(c);

        videoListService.addComment(videoListId, c);
        commentService.addAna(analyst, c);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", true);

        return jsonObject.toString();
    }

    @RequestMapping("/list/commentDelete")
    @ResponseBody
    public String deleteComment(@RequestParam String comment_id,
                                @RequestParam String id,
                                @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:user") User user,
                                Model model){

        Long videoListId = Long.valueOf(id).longValue();
        Long commentId = Long.valueOf(comment_id).longValue();

        Comment comment = commentService.find(commentId);
        Analyst analyst = analystService.findAna(user.getUserId());

        Analyst ana1 = comment.getAnalyst_id();


        if(ana1 != analyst){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("result", false);
            model.addAttribute("error", "wrong.auth");
            return "/Sample/detail";
        }

        commentService.removeFromList(videoListId, comment);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", true);

        return jsonObject.toString();
    }
}