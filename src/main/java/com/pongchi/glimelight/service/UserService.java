package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.UserDto;
import com.pongchi.glimelight.api.v1.dto.UserLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.UserLoginResponseDto;
import com.pongchi.glimelight.api.v1.dto.UserRegisterRequestDto;
import com.pongchi.glimelight.domain.user.User;
import com.pongchi.glimelight.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    
    @Transactional
    public UUID register(UserRegisterRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.login(requestDto.getEmail(), requestDto.getPassword()).get();
        return new UserLoginResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto findById(UUID id) {
        User user = userRepository.findById(id).get();
        return new UserDto(user);
    }
}
