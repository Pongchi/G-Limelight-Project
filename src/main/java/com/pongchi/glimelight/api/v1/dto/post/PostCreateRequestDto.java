package com.pongchi.glimelight.api.v1.dto.post;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRequestDto {

    private UUID writerId;
    private String title;
    private String description = "";
    private String videoUrl;

    
    @Builder
    public PostCreateRequestDto(UUID writerId, String title, String description, String video_url) {
        this.writerId = writerId;
        this.title = title;
        this.description = description;
        this.videoUrl = video_url;
    }
}
