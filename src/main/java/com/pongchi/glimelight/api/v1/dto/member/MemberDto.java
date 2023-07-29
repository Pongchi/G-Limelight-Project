package com.pongchi.glimelight.api.v1.dto.member;

import java.util.UUID;

import com.pongchi.glimelight.domain.member.Member;

import lombok.Getter;

@Getter
public class MemberDto {

    private UUID id;
    private String nickname;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
    }
}