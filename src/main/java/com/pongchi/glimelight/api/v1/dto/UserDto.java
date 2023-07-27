package com.pongchi.glimelight.api.v1.dto;

import java.util.UUID;

import com.pongchi.glimelight.domain.user.User;

import lombok.Getter;

@Getter
public class UserDto {

    private UUID id;
    private String nickname;

    public UserDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
    }
}