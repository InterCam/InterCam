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
        return "list/analyst";
    }

    @GetMapping("/admin/analyst")
    public String save(){
        return "Login/AddAnalyst";
    }

    @PostMapping("/admin/analyst")
    public String saveAnalyst(AnalystRequestDto analystRequestDto){

        analystService.save(analystRequestDto);

        return "redirect:/";
    }
}
