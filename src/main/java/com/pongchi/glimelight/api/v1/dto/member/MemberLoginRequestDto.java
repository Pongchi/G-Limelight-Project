package com.pongchi.glimelight.api.v1.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginRequestDto {
    
    private String email;
    private String password;

    @Builder
    public MemberLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
