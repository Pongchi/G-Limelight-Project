package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.ResponseDto;
import com.pongchi.glimelight.api.v1.dto.ResponsesDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    
    @PostMapping("/api/v1/members")
    public ResponseEntity<?> register(@Valid MemberRegisterRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponsesDto.createResponseEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors().toArray()
            );
        }

        UUID uuid = memberService.register(requestDto);
        return ResponseDto.createResponseEntity(
            ResponseCode.SUCCESS,
            uuid
        );
    }

    @GetMapping("/api/v1/members/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") UUID id) {
        return ResponseDto.createResponseEntity(
            ResponseCode.SUCCESS,
            memberService.findById(id)
        );
    }

    @PostMapping("/api/v1/members/login")
    public ResponseEntity<?> login(@Valid MemberLoginRequestDto requestDto, BindingResult bindingResult) {
        return ResponseDto.createResponseEntity(
            ResponseCode.SUCCESS,
            memberService.login(requestDto)
        );
    }
}
