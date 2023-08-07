package com.pongchi.glimelight.api.v1.dto.comment;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreationDto {
    
    private UUID memberId;
    private UUID postId;
    private String message;
}
