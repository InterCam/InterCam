package com.example.intercam.service;

import com.example.intercam.Repository.UserRepository;
import com.example.intercam.Repository.VideoListRepository;
import com.example.intercam.Repository.VideoRepository;
import com.example.intercam.dto.UserResponseDto;
import com.example.intercam.dto.VideoResponseDto;
import com.example.intercam.entity.User;
import com.example.intercam.entity.Video;
import com.example.intercam.entity.VideoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
    public void saveVideo(String title, String url, User user){
        Video video = Video.builder().title(title).url(url).build();

        Video saved_video = videoRepository.save(video);

        User real_user = userRepository.findById(user.getUserId()).get();

        VideoList videoList = VideoList.builder().avgScore(0F).build();

        videoList.addVideo(saved_video);

        real_user.addVideoList(videoList);

        videoListRepository.save(videoList);
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

    @Transactional
    public List<VideoResponseDto> findMyVideo(UserResponseDto userResponseDto) {
        User user = userRepository.findByUsername(userResponseDto.getUsername());

        List<VideoList> videoLists = user.getList_id();
        List<VideoResponseDto> videoResponseDtos = new ArrayList<>();

        for(VideoList tmp:videoLists){
            videoResponseDtos.add(new VideoResponseDto(tmp));
        }

        return videoResponseDtos;
    }

    @Transactional
    public void checkfile(UserResponseDto userResponseDto) {
        try {
            User user = userRepository.findByUsername(userResponseDto.getUsername());

            String url = user.getList_id().get(0).getVideo_id().getUrl();
            URL url1 = new URL(url);
            URLConnection conn1 = url1.openConnection();
            HttpURLConnection exitCode = (HttpURLConnection) conn1;

            if(exitCode.getResponseCode()==200){
                return;
            }

            String url_r = url.replace("hybucket", "checkvideofile");
            URL url2 = new URL(url_r);
            URLConnection conn2 = url2.openConnection();
            HttpURLConnection exitCode2 = (HttpURLConnection) conn2;

            if(exitCode2.getResponseCode()!=200){
                throw new IllegalArgumentException("파일이 존재하지 않습니다");
            }

            Video video = videoRepository.findByUrl(url);

            video.setUrl(url_r);
            String graph = url_r.replace(".mp4",".svg");
            video.setGraph(graph);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}

