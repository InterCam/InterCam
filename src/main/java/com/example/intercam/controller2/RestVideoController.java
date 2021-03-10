package com.example.intercam.controller2;

import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RestVideoController {

    private final CommentService commentService;

    @PostMapping("/list/detail")
    public String comment(@RequestParam Long id,
                          @RequestParam Integer score,
                          @RequestParam String comment,
                          @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto") UserResponseDto userResponseDto,
                          Model model) {
        return commentService.save(id, userResponseDto, score, comment);
    }

    @PostMapping("/list/commentDelete")
    public String deleteComment(@RequestParam Long comment_id,
                                @RequestParam String id,
                                @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto") UserResponseDto userResponseDto,
                                Model model) {

        return commentService.delete(comment_id, id, userResponseDto);
    }
}
