package com.pongchi.glimelight.api.v1.dto.member;

import java.util.UUID;

import com.pongchi.glimelight.domain.member.Member;

import lombok.Getter;

@Getter
public class MemberDto {

    private UUID id;
    private String email;
    private String nickname;
    private long mySubscribes_count = 0;
    private long otherSubscribes_count = 0;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.mySubscribes_count = member.getMySubscribes_count();
        this.otherSubscribes_count = member.getOtherSubscribes_count();
    }
}