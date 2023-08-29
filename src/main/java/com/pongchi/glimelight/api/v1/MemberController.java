package com.pongchi.glimelight.api.v1;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.exception.CustomExceptions;
import com.pongchi.glimelight.service.MemberService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    
    @PostMapping("/api/v1/members")
    public ResponseEntity<?> register(@Valid @RequestBody MemberRegisterRequestDto requestDto, BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }

        UUID uuid = memberService.register(requestDto);
        return createResponseEntity(
            ResponseCode.SUCCESS,
            uuid
        );
    }

    @GetMapping("/api/v1/members/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") @NotBlank UUID id, BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }

        return createResponseEntity(
            ResponseCode.SUCCESS,
            memberService.findById(id)
        );
    }

    @PostMapping("/api/v1/members/login")
    public ResponseEntity<?> login(@Valid @RequestBody MemberLoginRequestDto requestDto, BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }
        
        return createResponseEntity(
            ResponseCode.SUCCESS,
            memberService.login(requestDto)
        );
    }
}
