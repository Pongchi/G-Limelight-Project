package com.pongchi.glimelight.api.v1.dto.member;

import lombok.Getter;

@Getter
public class MemberLoginResponseDto {
    
    private String access_token;

    public MemberLoginResponseDto(String access_token) {
        this.access_token = access_token;
    }

}
