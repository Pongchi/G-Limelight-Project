package com.pongchi.glimelight.api.v1.dto;

import com.pongchi.glimelight.domain.member.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRegisterRequestDto {
    
    private String email;
    private String password;
    private String nickname;

    @Builder
    public MemberRegisterRequestDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public Member toEntity() {
        return Member.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .build();
    }
}
