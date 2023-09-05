package com.pongchi.glimelight.api.v1.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenLoginDto {
    
    private String accessToken;
    private String refreshToken;
}
