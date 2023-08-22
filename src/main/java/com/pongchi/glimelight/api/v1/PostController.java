package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;
import static com.pongchi.glimelight.api.v1.dto.ResponsesDto.createResponsesEntity;
import com.pongchi.glimelight.api.v1.dto.post.PostCreateRequestDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
    
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public ResponseEntity<?> create(@RequestBody PostCreateRequestDto requestDto) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            postService.create(requestDto)
        );
    }

    @GetMapping("/api/v1/posts")
    public ResponseEntity<?> postListAll(Pageable pageable) {
        return createResponsesEntity(
            ResponseCode.SUCCESS,
            postService.findAll(pageable).getContent()
        );
    }

    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<?> read(@PathVariable("id") UUID id) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            postService.read(id)
        );
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            postService.delete(id)
        );
    }
}
