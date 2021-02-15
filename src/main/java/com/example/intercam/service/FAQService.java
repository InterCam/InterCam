package com.example.intercam.service;

import com.example.intercam.dto.FAQResponseDto;
import com.example.intercam.entity.FAQ_repository;
import com.example.intercam.entity.faq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class FAQService {

    private final FAQ_repository FAQ_repository;

    //TODO 배포 때 지울 것
    @PostConstruct // 서비스 빈이 생성되고 바로 할 일.. (Test에 하는게 맞지만 ..)
    public void createFAQ(){
        faq faq_list1 = faq.builder()
                .title("동영상 신고는 어떻게 하나요?")
                .contents("팀이랑 상의.")
                .build();
        FAQ_repository.save(faq_list1);

        faq faq_list2 = faq.builder()
                .title("내가 올린 동영상을 확인하려면?")
                .contents("메인 페이지에서 '나의 동영상 목록'에서 확인하실 수 있습니다.")
                .build();
       FAQ_repository.save(faq_list2);

        faq faq_list3 = faq.builder()
                .title("점수 평가 기준은 어떻게 되나요?")
                .contents("팀이랑 상의.")
                .build();
        FAQ_repository.save(faq_list3);

        faq faq_list4 = faq.builder()
                .title("동영상이 타 사이트에 배포될 수 있나요?")
                .contents("InterCam에서는 개인정보보호를 위해 회원님의 동영상을 따로 저장하거나 무단 배포하지 않습니다.")
                .build();
        FAQ_repository.save(faq_list4);

    }

    public List<FAQResponseDto> get_faq() {

        List<faq> tmp = FAQ_repository.findAll();
        List<FAQResponseDto> list = new ArrayList<>();

        for(faq ch:tmp){
            list.add(new FAQResponseDto(ch));
        }

        return list;
    }

    public faq findFAQ(Integer num){
        Optional<faq> findFaq = FAQ_repository.findById(num);
        return findFaq.get();
    }
}
