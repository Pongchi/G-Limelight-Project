package com.pongchi.glimelight.api.v1.dto.member;

import java.util.UUID;

import com.pongchi.glimelight.domain.member.Member;

import lombok.Getter;

@Getter
public class MemberLoginResponseDto {
    
    private UUID id;
    private String nickname;

    public MemberLoginResponseDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
    }

}
