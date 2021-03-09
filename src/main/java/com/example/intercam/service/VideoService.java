package com.example.intercam.service;

import com.example.intercam.Repository.UserRepository;
import com.example.intercam.Repository.VideoListRepository;
import com.example.intercam.Repository.VideoRepository;
import com.example.intercam.entity.User;
import com.example.intercam.entity.Video;
import com.example.intercam.entity.VideoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    //DB에 저장 하는 로직
    private final VideoRepository videoRepository;
    private final VideoListRepository videoListRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveVideo(Video video) {
        videoRepository.save(video);
    }


    @Transactional
    public void saveVideoList(VideoList videoList) { videoListRepository.save(videoList);}


    @Transactional
    public List<VideoList> getVideoList(User user) {
        Optional<VideoList> videoLists =  videoListRepository.findById(user.getUserId());
        if(videoLists.isEmpty()) {
            throw new IllegalStateException("아직 올리신 영상이 없습니다.");
        }

        VideoList videoList = videoLists.get();

        List<VideoList> list = user.getList_id();

        return list;
    }

}

