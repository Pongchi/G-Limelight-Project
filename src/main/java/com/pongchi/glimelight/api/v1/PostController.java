package com.pongchi.glimelight.api.v1;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.post.PostCreateRequestDto;
import com.pongchi.glimelight.api.v1.dto.post.PostResponseDto;
import com.pongchi.glimelight.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
    
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public UUID create(@RequestBody PostCreateRequestDto requestDto) {
        return postService.create(requestDto);
    }

    @GetMapping("/api/v1/posts")
    public List<PostResponseDto> postListAll(Pageable pageable) {
        return postService.findAll(pageable).getContent();
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto read(@PathVariable("id") UUID id) {
        return postService.read(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public PostResponseDto delete(@PathVariable("id") UUID id) {
        return postService.delete(id);
    }
}
