package com.example.intercam.service;

import com.example.intercam.dto.FilesSaveRequestDto;
import com.example.intercam.entity.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;
    @Transactional
    public void save(List<MultipartFile> files) throws Exception{

        for(MultipartFile file:files){
            file.transferTo(new File("C:\\Users\\임동진\\Desktop\\프로그래밍\\웹앱개발\\"
                    +file.getOriginalFilename()));

            FilesSaveRequestDto requestDto = FilesSaveRequestDto.builder()
                    .filename(file.getOriginalFilename())
                    .filePath("C:\\Users\\임동진\\Desktop\\프로그래밍\\웹앱개발\\"
                            +file.getOriginalFilename())
                    .build();

            fileRepository.save(requestDto.toEntity());
        }
    }

}
