package com.example.intercam.service;

import com.example.intercam.Repository.AnalysisRepository;
import com.example.intercam.Repository.UserRepository;
import com.example.intercam.dto.AnalystRequestDto;
import com.example.intercam.dto.AnalystResponseDto;
import com.example.intercam.entity.Analyst;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnalystService {

    private final AnalysisRepository analysisRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void save(AnalystRequestDto analystRequestDto){

        if(userRepository.findByUsername(analystRequestDto.getUsername())!=null){
            throw new IllegalArgumentException("존재하는 아이디");
        }

        analystRequestDto.setPassword(bCryptPasswordEncoder.encode(analystRequestDto.getPassword()));

        analysisRepository.save(analystRequestDto.toEntity());
    }

    @Transactional
    public List<AnalystResponseDto> findAll(){

        List<Analyst> analysts = analysisRepository.findAll();
        List<AnalystResponseDto> result = new ArrayList<>();
        for(Analyst tmp:analysts){
            result.add(new AnalystResponseDto(tmp));
        }

        return result;
    }

    public Analyst findAna(Long id){
        Optional<Analyst> analyst = analysisRepository.findById(id);

        if(analyst == null){
            return null;
        }
        return analyst.get();
    }
}
