package com.example.intercam.controller;

import com.example.intercam.dto.AnalystRequestDto;
import com.example.intercam.dto.AnalystResponseDto;
import com.example.intercam.service.AnalystService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnalystController {

    private final AnalystService analystService;

    @GetMapping("/analyst")
    public String addAnalyst(Model model){
        List<AnalystResponseDto> analysts = analystService.findAll();
        model.addAttribute("analysts", analysts);
        return "intro/analyst";
    }

    //TODO
    @GetMapping("/admin/analyst")
    public String save(){
        return "Login/AddAnalyst";
    }

    //TODO img파일 저장 & 경로 저장
    @PostMapping("/admin/analyst")
    public String saveAnalyst(AnalystRequestDto analystRequestDto){

        analystService.save(analystRequestDto);

        return "redirect:/";
    }
}
