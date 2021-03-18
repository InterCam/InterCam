package com.example.intercam.service;

import com.example.intercam.Repository.CommentRepository;
import com.example.intercam.Repository.VideoListRepository;
import com.example.intercam.dto.VideoResponseDto;
import com.example.intercam.entity.Comment;
import com.example.intercam.entity.VideoList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoListService {
    private static final int BLOCK_PAGE_NUM_COUNT = 10;
    private static final int PAGE_POST_COUNT = 10;

    private final VideoListRepository videoListRepository;
    private final CommentRepository commentRepository;
    private final VideoService videoService;

    @Transactional
    public List<VideoResponseDto> getVideoList(Integer page) {
        int real_page = 0;
        if(page==null){
            real_page = 1;
        }else {
            real_page = page;
        }

        List<VideoList> videoLists = videoListRepository.findAll(Sort.by("listId").descending());

        int start = 10*(real_page-1);
        int end = 10*real_page;

        List<VideoResponseDto> videoResponseDtos = new ArrayList<>();

        for(int index=start;index<end;index++){
            if(index>=videoLists.size()){
                break;
            }
            videoResponseDtos.add(new VideoResponseDto(videoLists.get(index)));
        }

        return videoResponseDtos;
    }

    @Transactional
    public List<VideoResponseDto> getVideoRankList() {

        List<VideoList> videoLists = videoListRepository.findAll(Sort.by("avgScore").descending());
        List<VideoResponseDto> videoResponseDtos = new ArrayList<>();

        for(int i =0;i<5;i++){
            if(i>=videoLists.size()){
                break;
            }
            videoService.checkfile(videoLists.get(i).getListId());

            videoResponseDtos.add(new VideoResponseDto(videoLists.get(i)));
        }

        return videoResponseDtos;
    }


    @Transactional
    public VideoResponseDto avgScore(Long id){
        VideoList videoList = videoListRepository.findById(id).get();

        List<Comment> comments = commentRepository.findAll();

        List<Comment> commentList = videoList.getCommentList();

        if(commentList.size()!=0) {
            int sum = 0;
            for (Comment c : commentList) {
                sum += c.getScore();
            }
            float avg = sum / commentList.size();

            videoList.setAvgScore(avg);
        } else{
            videoList.setAvgScore(0F);
        }

        return new VideoResponseDto(videoList);
    }

    @Transactional
    public VideoList findVideo(Long id){
        Optional<VideoList> videoList = videoListRepository.findById(id);

        return videoList.get();
    }

    @Transactional
    public void addComment(Long id , Comment comment){
        VideoList videoList = videoListRepository.findById(id).get();

        videoList.addComment(comment);

        commentRepository.save(comment);
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
