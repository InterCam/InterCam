package com.example.intercam.controller;

import com.example.intercam.dto.NoticeRequestDto;
import com.example.intercam.dto.NoticeResponseDto;
import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model,
                       @AuthenticationPrincipal(expression = "#this=='anonymousUser'?null:userResponseDto")
                               UserResponseDto userResponseDto){

        if(userResponseDto == null){
            model.addAttribute("auth",null);

        } else {
            model.addAttribute("auth", userResponseDto.getAuth().toString());
        }

        List<NoticeResponseDto> noticeList = noticeService.getNoticeList(page);
        Integer[] pageList = noticeService.getPageList(page);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("pageList", pageList);

        return "customer/noticelist";
    }

    @GetMapping("/notice/contents")
    public String notice(Long id, Model model){
        NoticeResponseDto notice = noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "customer/contents_notice";
    }

    @GetMapping("/admin/notice/write")
    public String notice(){
        return "customer/write";
    }

    @PostMapping("/admin/notice/write")
    public String save(NoticeRequestDto noticeRequestDto){
        noticeService.saveNotice(noticeRequestDto);

        return "redirect:/notice";
    }
}
