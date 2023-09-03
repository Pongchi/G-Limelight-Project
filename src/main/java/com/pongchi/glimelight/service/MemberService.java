package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import com.pongchi.glimelight.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    
    @Transactional
    public UUID register(MemberRegisterRequestDto requestDto) {
        if (memberRepository.existsByEmail( requestDto.getEmail() )) {
            throw new CustomException(ResponseCode.CONCLICT_MEMBER);
        }

        Member member = requestDto.toEntity();
        member.hashPassword(passwordEncoder);

        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_MEMBER)
            );

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new CustomException(ResponseCode.UNAUTHENTICATION_FAIL);
        }

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                requestDto.getEmail(),
                requestDto.getPassword()
            )
        );
        String access_token = jwtTokenProvider.generateToken(authentication);
        return new MemberLoginResponseDto(access_token);
    }

    @Transactional(readOnly = true)
    public MemberDto findById(String stringId) {
        UUID id = UUID.fromString(stringId);
        Member member = memberRepository.findById(id)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_MEMBER)
            );
        return new MemberDto(member);
    }
}
