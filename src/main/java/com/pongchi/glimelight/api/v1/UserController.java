package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.UserLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.UserLoginResponseDto;
import com.pongchi.glimelight.api.v1.dto.UserRegisterRequestDto;
import com.pongchi.glimelight.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

    private UserService userService;
    
    // 유저 회원가입 -> return 유저 ID
    @PostMapping("/api/v1/users")
    public UUID register(@RequestBody UserRegisterRequestDto requestDto) {
        return userService.register(requestDto);
    }

    // 유저 로그인 - GET 메소드를 써야할 것 같지만 보안상의 이유로 POST
    @PostMapping("/api/v1/user/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return userService.login(requestDto);
    }
}
