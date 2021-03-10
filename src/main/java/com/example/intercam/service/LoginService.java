package com.example.intercam.service;

import com.example.intercam.Repository.UserRepository;
import com.example.intercam.dto.ChangeResponseDto;
import com.example.intercam.dto.UserJoinDto;
import com.example.intercam.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public String join(UserJoinDto userJoinDto){
        userJoinDto.setPassword(bCryptPasswordEncoder.encode(userJoinDto.getPassword()));

        User user = userJoinDto.toEntity();

        String username = userRepository.save(user).getUsername();

        return username;
    }

    @Transactional
    public String find(ChangeResponseDto changeResponseDto) {
        User user = userRepository.findByUsername(changeResponseDto.getUsername());

        if(user==null){
            throw new IllegalArgumentException("존재하지 않는 유저입니다!");
        }
        if(user.getName().equals(changeResponseDto.getName()) && user.getPhone().equals(changeResponseDto.getPhone())){
            String uuid = UUID.randomUUID().toString();

            String encode_uuid = bCryptPasswordEncoder.encode(uuid);

            user.changePassword(encode_uuid);

            return uuid;
        }else{
            throw new IllegalArgumentException("존재하지 않는 유저입니다!");
        }
    }
}
