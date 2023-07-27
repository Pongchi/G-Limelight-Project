package com.pongchi.glimelight.service;

import com.pongchi.glimelight.api.v1.dto.UserRegisterRequestDto;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
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