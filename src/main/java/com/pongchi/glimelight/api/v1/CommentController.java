package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;
import static com.pongchi.glimelight.api.v1.dto.ResponsesDto.createResponsesEntity;

import com.pongchi.glimelight.api.v1.dto.comment.CommentCreationDto;
import com.pongchi.glimelight.api.v1.dto.comment.CommentOnPostRequestDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {
    
    private final CommentService commentService;

    @GetMapping("/api/v1/comments")
    public ResponseEntity<?> getCommentsOnPost(CommentOnPostRequestDto requestDto) {
        return createResponsesEntity(
            ResponseCode.SUCCESS,
            commentService.getCommentsOnPost(requestDto)
        );
    }

    @PostMapping("/api/v1/comments")
    public ResponseEntity<?> create(CommentCreationDto requestDto) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            commentService.create(requestDto)
        );
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            commentService.delete(id)
        );
    }
}
