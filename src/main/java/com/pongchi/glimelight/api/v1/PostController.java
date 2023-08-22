package com.pongchi.glimelight.api.v1;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;
import static com.pongchi.glimelight.api.v1.dto.ResponsesDto.createResponsesEntity;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.post.PostCreateRequestDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.service.PostService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
    
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public ResponseEntity<?> create(@Valid @RequestBody PostCreateRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }

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
    public ResponseEntity<?> read(@NotBlank @PathVariable("id") UUID id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }

        return createResponseEntity(
            ResponseCode.SUCCESS,
            postService.read(id)
        );
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<?> delete(@NotBlank @PathVariable("id") UUID id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }
        
        return createResponseEntity(
            ResponseCode.SUCCESS,
            postService.delete(id)
        );
    }
}
