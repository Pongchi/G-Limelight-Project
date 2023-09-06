package com.pongchi.glimelight.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;

@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TokenService tokenService;

    @Test
    public void 로그인() throws Exception {
        // givenr
        String email = "test@gmail.com";
        String password = "test";
        String nickname = "test";
        MemberRegisterRequestDto registerDto = new MemberRegisterRequestDto(email, password, nickname);
        memberService.register(registerDto);

        MemberLoginRequestDto requestDto = new MemberLoginRequestDto(email, password);

        // when & then
        tokenService.login(requestDto);
    }
}
