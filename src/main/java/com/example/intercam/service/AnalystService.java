package com.example.intercam.service;

import com.example.intercam.Repository.AnalysisRepository;
import com.example.intercam.Repository.UserRepository;
import com.example.intercam.dto.AnalystRequestDto;
import com.example.intercam.dto.AnalystResponseDto;
import com.example.intercam.entity.Analyst;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AnalystService {

    private final AnalysisRepository analysisRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void save(MultipartFile file, AnalystRequestDto analystRequestDto) throws Exception{
        File file1 = new File("./src/main/resources/static/Images");
        file.transferTo(new File(file1.getAbsolutePath()+"/"+analystRequestDto.getName()+".jpg"));

        analystRequestDto.setPassword(bCryptPasswordEncoder.encode(analystRequestDto.getPassword()));

        analysisRepository.save(analystRequestDto.toEntity());
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<AnalystResponseDto> findAll(){
        List<Analyst> analysts = analysisRepository.findAll();
        List<AnalystResponseDto> result = new ArrayList<>();
        for(Analyst tmp:analysts){
            result.add(new AnalystResponseDto(tmp));
        }
        return result;
    }

    public Analyst findAna(String username){
        return analysisRepository.findByUsername(username);
    }
}
