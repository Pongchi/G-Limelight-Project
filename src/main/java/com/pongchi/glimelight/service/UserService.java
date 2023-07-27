package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pongchi.glimelight.api.v1.dto.UserRegisterRequestDto;
import com.pongchi.glimelight.domain.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    
    @Transactional
    public UUID register(UserRegisterRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()
                .getId());
    }
}
