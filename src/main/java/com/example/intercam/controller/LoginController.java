package com.example.intercam.controller;

import com.example.intercam.dto.UserJoinDto;
import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/join") //회원가입
    public String join(){
        return "HTML/starterkit/dist/auth-register_r";
    }

    @PostMapping("/join") //회원가입처리
    public String join(UserJoinDto userJoinDto){

        loginService.join(userJoinDto);

        return "redirect:/login";
    }

    @GetMapping("/login") //로그인
    public String login(@AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto") UserResponseDto userResponseDto){

        return "HTML/starterkit/dist/auth-login_r";
    }

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
//    //인증 방법 나중에 구현

}
