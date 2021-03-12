package com.example.intercam.service;

import com.example.intercam.dto.NoticeRequestDto;
import com.example.intercam.dto.NoticeResponseDto;
import com.example.intercam.dto.VideoResponseDto;
import com.example.intercam.entity.Notice;
import com.example.intercam.Repository.NoticeRepository;
import com.example.intercam.entity.VideoList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private static final int BLOCK_PAGE_NUM_COUNT = 10;
    private static final int PAGE_POST_COUNT = 10;
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

    public Integer[] getPageList(Integer currentPage) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        List<Notice> count = noticeRepository.findAll();
        Double totalList = Double.valueOf(count.size());

        Integer totalLastPageNum = (int)(Math.ceil(totalList/PAGE_POST_COUNT));

        Integer blockLastPageNum = (totalLastPageNum > currentPage + BLOCK_PAGE_NUM_COUNT)?
                currentPage + BLOCK_PAGE_NUM_COUNT : totalLastPageNum;

        currentPage = (currentPage <= 3) ? 1: currentPage-2;

        for(int val=currentPage, i = 0; val <= blockLastPageNum; val++, i++){
            pageList[i] = val;
        }
        return pageList;
    }

    @Transactional
    public List<NoticeRequestDto> getNoticeList(Integer page) {
        int real_page = 0;
        if(page==null){
            real_page = 1;
        }else {
            real_page = page;
        }

        List<Notice> noticeList = noticeRepository.findAll(Sort.by("id").descending());

        int start = 10*(real_page-1);
        int end = 10*real_page;

        List<NoticeRequestDto> noticeRequestDto = new ArrayList<>();

        for(int index=start;index<end;index++){
            if(index>=noticeList.size()){
                break;
            }
            noticeRequestDto.add(new NoticeRequestDto(noticeList.get(index)));
        }
        return noticeRequestDto;
    }
}

//TODO 후순위. Notice 수정 기능

