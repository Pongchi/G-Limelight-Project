package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;
import static com.pongchi.glimelight.api.v1.dto.ResponsesDto.createResponsesEntity;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.service.LikeService;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LikeController {
    
    private final LikeService likeService;

    @GetMapping("/api/v1/like")
    public ResponseEntity<?> check(@NotBlank @RequestParam UUID postId, @NotBlank @RequestParam UUID memberId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }

        return createResponseEntity(
            ResponseCode.SUCCESS,
            likeService.check(postId, memberId)
        );
    }
    
    @PostMapping("/api/v1/like")
    public ResponseEntity<?> addLike(@NotBlank @RequestParam UUID postId, @NotBlank @RequestParam UUID memberId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }

        return createResponseEntity(
            ResponseCode.SUCCESS,
            likeService.add(postId, memberId)
        );
    }

    @DeleteMapping("/api/v1/like")
    public ResponseEntity<?> subLike(@NotBlank @RequestParam UUID postId, @NotBlank @RequestParam UUID memberId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }
        
        return createResponseEntity(
            ResponseCode.SUCCESS,
            likeService.subtract(postId, memberId)
        );
    }
}
