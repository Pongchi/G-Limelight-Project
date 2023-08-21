package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.member.MemberDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginResponseDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;
import com.pongchi.glimelight.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    
    // 유저 회원가입 -> return 유저 ID
    @PostMapping("/api/v1/members")
    public ResponseEntity register(@Valid MemberRegisterRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok()
                .body(memberService.register(requestDto));
    }

    @GetMapping("/api/v1/members/{id}")
    public ResponseEntity<MemberDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok()
                .body(memberService.findById(id));
    }

    @PostMapping("/api/v1/members/login")
    public ResponseEntity<MemberLoginResponseDto> login(@Valid MemberLoginRequestDto requestDto, BindingResult bindingResult) {
        return ResponseEntity.ok()
                .body(memberService.login(requestDto));
    }
}
