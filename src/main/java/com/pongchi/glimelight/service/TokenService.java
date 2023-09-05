package com.pongchi.glimelight.service;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginResponseDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.exception.CustomException;
import com.pongchi.glimelight.jwt.JwtTokenProvider;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final Environment env;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto, HttpServletResponse response) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_MEMBER)
            );

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new CustomException(ResponseCode.AUTHENTICATION_FAIL);
        }

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                requestDto.getEmail(),
                requestDto.getPassword()
            )
        );
        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(requestDto.getEmail());

        Cookie cookie = new Cookie("REFRESHTOKEN",refreshToken);

        cookie.setMaxAge(
            Integer.parseInt(
                env.getProperty("jwt.token.refresh-expire-length")
            )
        );

        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        
        response.addCookie(cookie);
        return new MemberLoginResponseDto(accessToken);
    }

    public MemberLoginResponseDto refresh(String accessToken , String refreshToken) {
        // 액세스토큰은 만료되었으면서, 리프레시토큰은 만료되지 않았을 경우
        if (!jwtTokenProvider.validateToken(accessToken, true)
            &&
            jwtTokenProvider.validateToken(refreshToken, false)) {

            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            String refreshedAccessToken = jwtTokenProvider.generateAccessToken(authentication);

            return new MemberLoginResponseDto(refreshedAccessToken);
        } else {
            throw new CustomException(ResponseCode.ACCESS_TOKEN_NOT_EXPIRED);
        }
    }
}
