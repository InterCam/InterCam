package com.example.intercam.service;

import com.example.intercam.dto.UserJoinDto;
import com.example.intercam.entity.User;
import com.example.intercam.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

        user.changePassword(bCryptPasswordEncoder.encode(password));

    }
}
