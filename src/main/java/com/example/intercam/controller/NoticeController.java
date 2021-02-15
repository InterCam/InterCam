package com.example.intercam.controller;

import com.example.intercam.dto.NoticeRequestDto;
import com.example.intercam.dto.NoticeResponseDto;
import com.example.intercam.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String list(Model model){
        List<NoticeResponseDto> noticeList = noticeService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "list/noticelist";
    }

    @GetMapping("/notice/contents")
    public String notice(Long id, Model model){
        NoticeResponseDto notice = noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "Sample/contents_notice";
    }

    
    
    //TODO 공지사항 올리는 View 구현
    @GetMapping("/admin/notice/write")
    public String notice(){
        return "Sample/write";
    }

    @PostMapping("/admin/notice/write")
    public String save(NoticeRequestDto noticeRequestDto){
        noticeService.saveNotice(noticeRequestDto);

        return "redirect:/notice";
    }
}
