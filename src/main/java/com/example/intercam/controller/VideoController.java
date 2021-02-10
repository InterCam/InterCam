package com.example.intercam.controller;

import com.example.intercam.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class VideoController {

    private final FileService fileService;

    @GetMapping("/video")
    public String video(){
        return "file";
    }

    @PostMapping("/api/v1/upload")
    public String savefile(MultipartHttpServletRequest request) throws Exception{

        List<MultipartFile> files = request.getFiles("file");

        fileService.save(files);

        return "redirect:/";
    }


}
