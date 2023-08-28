package com.pongchi.glimelight.api.v1.dto.subscribe;

import java.util.Optional;

import com.pongchi.glimelight.domain.subscribe.Subscribe;

import lombok.Getter;

@Getter
public class SubscribeCheckDto {
    
    private boolean check;

    public SubscribeCheckDto(Optional<Subscribe> subscribe) {
        check = (subscribe.isPresent() ? true : false);
    }
}
