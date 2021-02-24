package com.example.intercam.controller;

import com.example.intercam.dto.NoticeRequestDto;
import com.example.intercam.dto.NoticeResponseDto;
import com.example.intercam.entity.Notice;
import com.example.intercam.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String list(@PageableDefault Pageable pageable, Model model){

        Page<Notice> list = noticeService.findAllDesc(pageable);

        model.addAttribute("noticeList", list);

        return "list/noticelist";
    }

    @GetMapping("/notice/contents")
    public String notice(Long id, Model model){
        NoticeResponseDto notice = noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "Sample/contents_notice";
    }

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
