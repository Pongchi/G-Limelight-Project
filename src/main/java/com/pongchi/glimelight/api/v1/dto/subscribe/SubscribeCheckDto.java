package com.pongchi.glimelight.api.v1.dto.subscribe;

import lombok.Getter;

@Getter
public class SubscribeCheckDto {
    
    private boolean check;

    public SubscribeCheckDto(boolean check) {
        this.check = check;
    }
}
