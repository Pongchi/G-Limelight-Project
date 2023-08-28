package com.pongchi.glimelight.api.v1.dto.like;

import java.util.Optional;

import com.pongchi.glimelight.domain.like.Like;

import lombok.Getter;

@Getter
public class LikeCheckDto {

    private boolean result;

    public LikeCheckDto(Optional<Like> like) {
        result = (like.isEmpty() ? false : true);
    }
    
}
