package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.member.MemberDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginResponseDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;
import com.pongchi.glimelight.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    
    // 유저 회원가입 -> return 유저 ID
    @PostMapping("/api/v1/members")
    public ResponseEntity<UUID> register(String email, String password, String nickname) {
        MemberRegisterRequestDto requestDto = MemberRegisterRequestDto.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();

        return ResponseEntity.ok()
                .body(memberService.register(requestDto));
    }

    @GetMapping("/api/v1/members/{id}")
    public ResponseEntity<MemberDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok()
                .body(memberService.findById(id));
    }

    // 유저 로그인 - GET 메소드를 써야할 것 같지만 보안상의 이유로 POST
    @PostMapping("/api/v1/members/login")
    public ResponseEntity<MemberLoginResponseDto> login(String email, String password) throws Exception {
        MemberLoginRequestDto requestDto = MemberLoginRequestDto.builder()
                .email(email)
                .password(password)
                .build();
        return ResponseEntity.ok()
                .body(memberService.login(requestDto));
    }
}
