package com.example.intercam.controller;


import com.example.intercam.dto.FAQResponseDto;
import com.example.intercam.entity.faq;
import com.example.intercam.service.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class FAQController {

    private final FAQService faqService;

    @GetMapping("/faq")
    public String faq(Model model){
        List<FAQResponseDto> faqList = faqService.get_faq();
        model.addAttribute("faqList", faqList);
        return "list/faqlist";
    }

    //TODO 아래로 내리는 것 구현하면 지움
    @GetMapping("/faq/contents")
    public String faq(Integer id, Model model){
        faq faq = faqService.findFAQ(id);

        model.addAttribute("faq", faq);

        return "Sample/contents";
    }

    //TODO 1. 등록을 DB에 직접? 아니면 따로 생성? 2. 따로 생성하면 View 페이지 생성
    @GetMapping("/admin/faq/register")
    public String register(){
        return null;
    }
}
