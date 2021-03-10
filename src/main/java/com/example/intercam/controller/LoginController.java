package com.example.intercam.controller;

import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/join")
    public String join(){
        return "Login/Join";
    }

    @GetMapping("/login") //로그인
    public String login(@AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto") UserResponseDto userResponseDto){
        if(userResponseDto!=null){
            return "redirect:/";
        }

        return "Login/Login";
    }

    @GetMapping("/change")
    public String change(){
        return "Login/Change";
    }

}
