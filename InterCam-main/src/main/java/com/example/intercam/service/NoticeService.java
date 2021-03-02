package com.example.intercam.service;

import com.example.intercam.dto.NoticeRequestDto;
import com.example.intercam.dto.NoticeResponseDto;
import com.example.intercam.entity.Notice;
import com.example.intercam.Repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public NoticeResponseDto getNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("존재하지 않는 공지사항입니다!"));

        notice.plusViews();

        return new NoticeResponseDto(notice);
    }

    @Transactional(readOnly = true)
    public Page<Notice> findAllDesc(Pageable pageable) {
        int page = (pageable.getPageNumber()==0)? 0 :(pageable.getPageNumber()-1);

        pageable = PageRequest.of(page, 10, Sort.by("id").descending());

        return noticeRepository.findAll(pageable);
    }

    @Transactional
    public void saveNotice(NoticeRequestDto noticeRequestDto){
        noticeRepository.save(noticeRequestDto.toEntity());
    }
    
    //TODO 후순위. Notice 수정 기능
}
