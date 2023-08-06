package com.pongchi.glimelight.api.v1.dto.like;

import lombok.Getter;

@Getter
public class LikeCheckDto {

    private boolean result;

    public LikeCheckDto( boolean result) {
        this.result = result;
    }
    
}
