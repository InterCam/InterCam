package com.example.intercam.controller2;

import com.example.intercam.dto.AskRequestDto;
import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.mail.HtmlEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RestAskController {

    private final HtmlEmailService htmlEmailService;

    @PostMapping("/asking")
    public AskRequestDto asking(AskRequestDto askRequestDto, @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto")
            UserResponseDto userResponseDto){

        htmlEmailService.question(askRequestDto, userResponseDto);

        return askRequestDto;
    }
}
