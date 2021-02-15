package com.example.intercam.controller;

import com.example.intercam.dto.UserJoinDto;
import com.example.intercam.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/join")
    public String join(){
        return "Login/Join";
    }

    @PostMapping("/join") //TODO 회원가입 성공시 성공 alert띄우게!
    public String join(UserJoinDto userJoinDto){

        loginService.join(userJoinDto);

        return "redirect:/login";
    }

    @GetMapping("/login") //로그인
    public String login(){
        return "Login/Login";
    }

    //TODO 패스워드 변경 및 인증 방법 구현
//    @GetMapping("/change/password")
//    public String change(){
//        return "change";
//    }
//
//    @PostMapping("/change/password")
//    public void changing(@AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto")
//                                     UserResponseDto userResponseDto, String username){
//        loginService.change(userResponseDto.getUsername(), password);
//    }

}
