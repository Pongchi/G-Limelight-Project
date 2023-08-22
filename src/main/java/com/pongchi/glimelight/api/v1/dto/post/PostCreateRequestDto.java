package com.pongchi.glimelight.api.v1.dto.post;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRequestDto {

    @NotBlank(message = "'writerId'가 공백일 수 없습니다.")
    private UUID writerId;

    @NotBlank
    private String title;

    private String description = "";

    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#()?&//=]*)", message = "'videoUrl' 이 정규표현식에 일치하지 않습니다.")
    private String videoUrl;

    
    @Builder
    public PostCreateRequestDto(UUID writerId, String title, String description, String video_url) {
        this.writerId = writerId;
        this.title = title;
        this.description = description;
        this.videoUrl = video_url;
    }
}
