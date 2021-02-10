package com.example.intercam.controller;


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
        List<faq> faqList = faqService.get_faq();
        model.addAttribute("faqList", faqList);
        return "faq";
    }

    @GetMapping("/faq/contents")
    public String faq(Integer id, Model model){
        faq faq = faqService.findFAQ(id);

        model.addAttribute("faq", faq);

        return "contents";
    }
}
