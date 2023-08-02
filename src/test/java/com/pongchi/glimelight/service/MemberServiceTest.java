package com.pongchi.glimelight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pongchi.glimelight.api.v1.dto.member.MemberDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginResponseDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        // given
        MemberRegisterRequestDto requestDto = new MemberRegisterRequestDto("email", "password", "nickname");
        
        // when
        UUID memberId = memberService.register(requestDto);

        // then
        Member savedMember = memberRepository.findById(memberId).get();
        assertEquals(memberId, savedMember.getId());
    }

    @Test
    public void 로그인() {
        // given
        String email = "test@gmail.com";
        String password = "test";
        String nickname = "test";
        MemberRegisterRequestDto registerDto = new MemberRegisterRequestDto(email, password, nickname);
        UUID memberId = memberService.register(registerDto);

        MemberLoginRequestDto requestDto = new MemberLoginRequestDto(email, password);

        // when
        MemberLoginResponseDto responseDto = memberService.login(requestDto);

        // then
        assertEquals(responseDto.getId(), memberId);
        assertEquals(responseDto.getNickname(), nickname);
    }

    @Test
    public void 유저검색() {
        // given
        String email = "test@gmail.com";
        String password = "test";
        String nickname = "test";
        MemberRegisterRequestDto registerDto = new MemberRegisterRequestDto(email, password, nickname);
        UUID memberId = memberService.register(registerDto);

        // when
        MemberDto savedMember = memberService.findById(memberId);

        // then
        assertEquals(savedMember.getId(), memberId);
        assertEquals(savedMember.getEmail(), email);
        assertEquals(savedMember.getNickname(), nickname);
    }
    
}
