package com.example.intercam.service;

import com.example.intercam.dto.NoticeRequestDto;
import com.example.intercam.dto.NoticeResponseDto;
import com.example.intercam.entity.Notice;
import com.example.intercam.entity.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public List<NoticeResponseDto> getNoticeList(){
        List<Notice> noticeList = noticeRepository.findAll();
        List<NoticeResponseDto> responseDtos = new ArrayList<>();

        for(Notice tmp:noticeList){
            responseDtos.add(new NoticeResponseDto(tmp));
        }

        return responseDtos;
    }

    @Transactional
    public NoticeResponseDto getNotice(Long id) {
        Optional<Notice> notice  = noticeRepository.findById(id);

        return new NoticeResponseDto(noticeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 공지사항입니다!")));
    }

    @Transactional
    public void saveNotice(NoticeRequestDto noticeRequestDto){
        noticeRepository.save(noticeRequestDto.toEntity());
    }

    @PostConstruct
    @Transactional
    public void NoticeList() {
        Notice notice1 = Notice.builder().title( "interCam 공지사항").content("1번 내용").build();
        noticeRepository.save(notice1);

        Notice notice2 = Notice.builder().title( " interCam 이용방법").content("2번 내용").build();
        noticeRepository.save(notice2);

        Notice notice3 = Notice.builder().title( " interCam 영상설정공지  ").content("3번 내용").build();
        noticeRepository.save(notice3);
    }

    //수정 기능 추가
}
