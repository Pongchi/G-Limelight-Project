package com.pongchi.glimelight.api.v1;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.comment.CommentCreationDto;
import com.pongchi.glimelight.api.v1.dto.comment.CommentOnPostRequestDto;
import com.pongchi.glimelight.domain.comment.Comment;
import com.pongchi.glimelight.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {
    
    private final CommentService commentService;

    @GetMapping("/api/v1/comments")
    public List<Comment> getCommentsOnPost(CommentOnPostRequestDto requestDto) {
        return commentService.getCommentsOnPost(requestDto);
    }

    @PostMapping("/api/v1/comments")
    public Comment create(CommentCreationDto requestDto) {
        return commentService.create(requestDto);
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public Comment delete(@PathVariable UUID id) {
        return commentService.delete(id);
    }

}
