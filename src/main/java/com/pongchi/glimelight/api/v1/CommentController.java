package com.pongchi.glimelight.api.v1;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;
import static com.pongchi.glimelight.api.v1.dto.ResponsesDto.createResponsesEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.comment.CommentCreationDto;
import com.pongchi.glimelight.api.v1.dto.comment.CommentOnPostRequestDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.exception.CustomExceptions;
import com.pongchi.glimelight.service.CommentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {
    
    private final CommentService commentService;

    @GetMapping("/api/v1/comments")
    public ResponseEntity<?> getCommentsOnPost(@Valid @RequestBody CommentOnPostRequestDto requestDto, BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }

        return createResponsesEntity(
            ResponseCode.SUCCESS,
            commentService.getCommentsOnPost(requestDto)
        );
    }

    @PostMapping("/api/v1/comments")
    public ResponseEntity<?> create(@Valid @RequestBody CommentCreationDto requestDto, BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }

        return createResponseEntity(
            ResponseCode.SUCCESS,
            commentService.create(requestDto)
        );
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public ResponseEntity<?> delete(@NotBlank @PathVariable UUID id, BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }
        
        return createResponseEntity(
            ResponseCode.SUCCESS,
            commentService.delete(id)
        );
    }
}
