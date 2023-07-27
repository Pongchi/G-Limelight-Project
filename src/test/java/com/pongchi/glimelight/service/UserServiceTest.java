package com.pongchi.glimelight.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pongchi.glimelight.api.v1.dto.UserRegisterRequestDto;
import com.pongchi.glimelight.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootTest
public class UserServiceTest {
    
    private UserService userService;

    @Test
    public void 유저_회원가입() {
        // given
        String email = "test@email.com";
        String password = "test";
        String nickname = "test";
        UserRegisterRequestDto requestDto = new UserRegisterRequestDto(email, password, nickname);

        // when
        UUID newUserId = userService.register(requestDto);

        System.out.println("[유저_회원가입] 회원가입된 유저 ID : " + newUserId );

        // then
        assertThat(newUserId).isNotNull();
    }
}