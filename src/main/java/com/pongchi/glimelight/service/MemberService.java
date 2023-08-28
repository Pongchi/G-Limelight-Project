package com.pongchi.glimelight.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.member.MemberDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginResponseDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.exception.CustomException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public UUID register(MemberRegisterRequestDto requestDto) {
        requestDto.setPassword(
            passwordEncoder.encode(requestDto.getPassword())
        );
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_MEMBER)
            );

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
        }
        return new MemberLoginResponseDto(member);
    }

    @Transactional(readOnly = true)
    public MemberDto findById(UUID id) {
        Optional<Member> member = memberRepository.findById(id);

        if (member.isEmpty()) {
            throw new CustomException(ResponseCode.NOT_FOUND_MEMBER);
        }
        return new MemberDto(member.get());
    }
}
