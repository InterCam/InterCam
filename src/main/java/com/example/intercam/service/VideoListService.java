package com.example.intercam.service;

import com.example.intercam.Repository.VideoListRepository;
import com.example.intercam.entity.VideoList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoListService {
    private static final int BLOCK_PAGE_NUM_COUNT = 10;
    private static final int PAGE_POST_COUNT = 10;

    private final VideoListRepository videoListRepository;

    public void save(VideoList videoList){
        videoListRepository.save(videoList);
    }


    public List<VideoList> getVideoList(Integer pageNum) {
        Page<VideoList> page = videoListRepository.findAll(PageRequest.of(pageNum -1, PAGE_POST_COUNT
                , Sort.by(Sort.Direction.DESC, "listId")));

        List<VideoList> vl = page.getContent();
        List<VideoList> videoLists = new ArrayList<>();

        for(VideoList v : vl){
            videoLists.add(this.convertPageToList(v));
        }

        return videoLists;
    }

    public List<VideoList> getVideoRankList() {
        Page<VideoList> page = videoListRepository.findAll(PageRequest.of(0, PAGE_POST_COUNT
                , Sort.by(Sort.Direction.DESC, "avgScore")));

        List<VideoList> vl = page.getContent();
        List<VideoList> videoLists = new ArrayList<>();

        for(VideoList v : vl){
            videoLists.add(this.convertPageToList(v));
        }

        return videoLists;
    }


    private VideoList convertPageToList(VideoList videoList){
        VideoList vl = VideoList.builder()
                .major(videoList.getMajor())
                .build();

        vl.setListId(videoList.getListId());
        vl.setCommentList(videoList.getCommentList());
        vl.setUserId(videoList.getUserId());
        vl.setVideo_id(videoList.getVideo_id());
        vl.setAvgScore(videoList.getAvgScore());
        vl.setViews(videoList.getViews());
        return vl;
    }

    public Integer[] getPageList(Integer currentPage){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        List<VideoList> count = videoListRepository.findAll();
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
}