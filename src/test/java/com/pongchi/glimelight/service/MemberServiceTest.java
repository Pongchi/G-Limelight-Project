package com.pongchi.glimelight.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pongchi.glimelight.api.v1.dto.MemberRegisterRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootTest
public class MemberServiceTest {
    
    private MemberService memberService;

    // @Test
    public void 멤버_회원가입() {
        // given
        String email = "test@email.com";
        String password = "test";
        String nickname = "test";
        MemberRegisterRequestDto requestDto = new MemberRegisterRequestDto(email, password, nickname);

        // when
        UUID newMemberId = memberService.register(requestDto);

        System.out.println("[유저_회원가입] 회원가입된 유저 ID : " + newMemberId );

        // then
        assertThat(newMemberId).isNotNull();
    }
}