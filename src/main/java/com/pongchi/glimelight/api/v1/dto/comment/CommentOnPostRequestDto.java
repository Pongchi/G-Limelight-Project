package com.pongchi.glimelight.api.v1.dto.comment;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentOnPostRequestDto {
    
    @NotBlank
    private UUID postId;

    public CommentOnPostRequestDto(UUID postId) {
        this.postId = postId;
    }
}
