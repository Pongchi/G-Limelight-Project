package com.pongchi.glimelight.api.v1.dto;

import com.pongchi.glimelight.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegisterRequestDto {
    
    private String email;
    private String password;
    private String nickname;

    @Builder
    public UserRegisterRequestDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public User toEntity() {
        return User.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .build();
    }
}
