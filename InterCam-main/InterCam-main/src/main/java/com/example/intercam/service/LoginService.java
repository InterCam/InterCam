package com.example.intercam.service;

import com.example.intercam.dto.ChangeResponseDto;
import com.example.intercam.dto.UserJoinDto;
import com.example.intercam.entity.User;
import com.example.intercam.Repository.UserRepository;
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
    public void join(UserJoinDto userJoinDto){

        userJoinDto.setPassword(bCryptPasswordEncoder.encode(userJoinDto.getPassword()));

        User user = userJoinDto.toEntity();

        userRepository.save(user);

    }

    @Transactional
    public void change(String username, String password) {

        User user = userRepository.findByUsername(username);

        //user.changePassword(bCryptPasswordEncoder.encode(password));

    }

    @Transactional
    public String find(ChangeResponseDto changeResponseDto) {

        User user = userRepository.findByUsername(changeResponseDto.getUsername());

        if(user==null){
            return null;
        }

        if(user.getName().equals(changeResponseDto.getName()) && user.getPhone().equals(changeResponseDto.getPhone())){

            String uuid = UUID.randomUUID().toString();

            String encode_uuid = bCryptPasswordEncoder.encode(uuid);

            user.changePassword(encode_uuid);

            return uuid;
        }

        return null;
    }
}
