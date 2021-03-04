package com.example.intercam.controller;

import com.example.intercam.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {
    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto")
                        UserResponseDto userResponseDto, Model model){
        
        if(userResponseDto!=null){
            model.addAttribute("user",userResponseDto);
        }
        
        return "index";
    }

    @GetMapping("/Login")
    public String index(){
        return "index";
    }


    //테스트 할때 쓰기
    @GetMapping("/testing")
    public String text() { return "Sample/mylist";}
}
