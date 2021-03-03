package com.example.intercam.service;

import com.example.intercam.dto.FAQResponseDto;
import com.example.intercam.entity.FAQ;
import com.example.intercam.Repository.FAQ_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class FAQService {

    private final FAQ_repository FAQ_repository;

    public List<FAQResponseDto> get_faq() {
        List<FAQ> tmp = FAQ_repository.findAll();
        List<FAQResponseDto> list = new ArrayList<>();

        for(FAQ ch:tmp){
            list.add(new FAQResponseDto(ch));
        }

        return list;
    }
}
