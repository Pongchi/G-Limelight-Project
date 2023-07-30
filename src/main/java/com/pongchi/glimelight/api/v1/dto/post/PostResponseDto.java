package com.pongchi.glimelight.api.v1.dto.post;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private UUID id;
    private UUID writerId;
    private String title;
    private String descroption = "";
    private String videoUrl;

    @Builder
    public PostResponseDto(UUID id, UUID writerId, String title, String description, String videoUrl) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.descroption = description;
        this.videoUrl = videoUrl;
    }
}
