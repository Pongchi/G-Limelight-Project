package com.pongchi.glimelight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    
}
