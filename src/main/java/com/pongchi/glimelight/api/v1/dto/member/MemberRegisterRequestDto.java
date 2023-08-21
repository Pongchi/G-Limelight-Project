package com.pongchi.glimelight.api.v1.dto.member;

import com.pongchi.glimelight.domain.member.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRegisterRequestDto {
    
    @NotBlank(message = "이메일은 빈 값일 수 없습니다")
    @Email(message = "올바른 형식의 이메일 주소어야 합니다")
    private String email;

    @NotBlank(message = "비밀번호는 빈 값일 수 없습니다")
    private String password;
    private String nickname;

    @Builder
    public MemberRegisterRequestDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .build();
    }
}
