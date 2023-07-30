package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.member.MemberDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginResponseDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    
    @Transactional
    public UUID register(MemberRegisterRequestDto requestDto) {
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.login(requestDto.getEmail(), requestDto.getPassword()).get();
        return new MemberLoginResponseDto(member);
    }

    @Transactional(readOnly = true)
    public MemberDto findById(UUID id) {
        Member member = memberRepository.findById(id).get();
        return new MemberDto(member);
    }
}
