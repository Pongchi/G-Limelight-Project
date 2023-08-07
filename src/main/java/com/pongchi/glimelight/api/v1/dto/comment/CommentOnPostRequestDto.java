package com.pongchi.glimelight.api.v1.dto.comment;

import java.util.UUID;

import lombok.Getter;

@Getter
public class CommentOnPostRequestDto {
    
    private UUID postId;

    public CommentOnPostRequestDto(UUID postId) {
        this.postId = postId;
    }
}
