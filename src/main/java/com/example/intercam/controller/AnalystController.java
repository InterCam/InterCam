package com.example.intercam.controller;

import com.example.intercam.dto.AnalystRequestDto;
import com.example.intercam.dto.AnalystResponseDto;
import com.example.intercam.service.AnalystService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    @GetMapping("/admin/analyst")
    public String save(){
        return "Login/AddAnalyst";
    }

    @PostMapping("/admin/analyst")
    public String saveAnalyst(MultipartHttpServletRequest request, AnalystRequestDto requestDto) throws Exception{

        MultipartFile file = request.getFile("file");

        analystService.save(file, requestDto);

        return "redirect:/";
    }
}
