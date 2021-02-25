package com.example.intercam.controller;

import com.example.intercam.dto.ChangeResponseDto;
import com.example.intercam.dto.UserJoinDto;
import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.mail.HtmlEmailService;
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
     private final HtmlEmailService htmlEmailService;

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
    public String login(@AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto")
                                    UserResponseDto userResponseDto){

        if(userResponseDto!=null){
            return "redirect:/";
        }

        return "Login/Login";
    }

    //TODO 패스워드 변경 및 인증 방법 구현
    @GetMapping("/change/password")
    public String change(){
        return "Sample/change";
    }

    @PostMapping("/change/password")
    public String changing(ChangeResponseDto changeResponseDto){

        String uuid = loginService.find(changeResponseDto);

        if(uuid!=null){

            htmlEmailService.sendEmail(changeResponseDto, uuid);

            return "redirect:/";
        }
        else{
            return "redirect:/change/password";
        }
    }

}
