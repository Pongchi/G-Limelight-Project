package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.UserRegisterRequestDto;
import com.pongchi.glimelight.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

    private UserService userService;
    
    // 유저 회원가입
    @PostMapping("/api/v1/users")
    public UUID register(@RequestBody UserRegisterRequestDto requestDto) {
        return userService.register(requestDto);
    }

}
