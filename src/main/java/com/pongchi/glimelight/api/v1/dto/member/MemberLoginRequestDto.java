package com.pongchi.glimelight.api.v1.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginRequestDto {
    
    @NotBlank(message = "이메일은 빈 값일 수 없습니다")
    @Email(message = "올바른 형식의 이메일 주소어야 합니다")
    private String email;

    @NotBlank(message = "비밀번호는 빈 값일 수 없습니다")
    private String password;

    @Builder
    public MemberLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
