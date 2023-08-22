package com.pongchi.glimelight.api.v1.dto.comment;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreationDto {
    
    @NotBlank
    private UUID memberId;

    @NotBlank
    private UUID postId;

    @NotBlank
    private String message;
}
