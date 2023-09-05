package com.pongchi.glimelight.api.v1;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.member.MemberLoginRequestDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberLoginResponseDto;
import com.pongchi.glimelight.api.v1.dto.token.TokenLoginDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.exception.CustomExceptions;
import com.pongchi.glimelight.service.TokenService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final Environment env;
    private final TokenService tokenService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<?> login(@Valid @RequestBody MemberLoginRequestDto requestDto, BindingResult bindingResult, HttpServletResponse response) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }

        TokenLoginDto tokenDto = tokenService.login(requestDto);

        Cookie cookie = new Cookie("REFRESHTOKEN", tokenDto.getRefreshToken());
        cookie.setMaxAge(
            Integer.parseInt(
                env.getProperty("jwt.token.refresh-expire-length")
            )
        );

        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        
        response.addCookie(cookie);
        
        return createResponseEntity(
            ResponseCode.SUCCESS,
            new MemberLoginResponseDto(tokenDto.getAccessToken())
        );
    }
    
    @PostMapping("/api/v1/refresh")
    public ResponseEntity<?> refresh(@NotBlank @RequestBody String accessToken, @CookieValue("REFRESHTOKEN") String refreshToken) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            tokenService.refresh(accessToken, refreshToken)
        );
    }
}