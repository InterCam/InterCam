package com.example.intercam.controller;

import com.example.intercam.dto.UserResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto")
                        UserResponseDto userResponseDto, Model model){
        
        if(userResponseDto!=null){
            model.addAttribute("user",userResponseDto);
        }
        
        return "main";
    }

    @GetMapping("/Login")
    public String index(){
        return "main";
    }
}
