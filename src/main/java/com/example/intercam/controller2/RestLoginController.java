package com.example.intercam.controller2;

import com.example.intercam.dto.ChangeResponseDto;
import com.example.intercam.dto.UserJoinDto;
import com.example.intercam.mail.HtmlEmailService;
import com.example.intercam.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RestLoginController {

    private final LoginService loginService;
    private final HtmlEmailService htmlEmailService;

    @PostMapping("/join")
    public UserJoinDto join(@RequestBody UserJoinDto userJoinDto){
        String username = loginService.join(userJoinDto);

        System.out.println(username);

        return userJoinDto;
    }

    @PostMapping("/change")
    public ChangeResponseDto changing(@RequestBody ChangeResponseDto changeResponseDto){
        String uuid = loginService.find(changeResponseDto);

        htmlEmailService.sendEmail(changeResponseDto, uuid);

        return changeResponseDto;
    }
}
