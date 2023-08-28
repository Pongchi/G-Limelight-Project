package com.pongchi.glimelight.api.v1;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.exception.CustomExceptions;
import com.pongchi.glimelight.service.LikeService;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LikeController {
    
    private final LikeService likeService;

    @GetMapping("/api/v1/like")
    public ResponseEntity<?> check(@NotBlank @RequestParam UUID postId, @NotBlank @RequestParam UUID memberId, BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }

        return createResponseEntity(
            ResponseCode.SUCCESS,
            likeService.check(postId, memberId)
        );
    }
    
    @PostMapping("/api/v1/like")
    public ResponseEntity<?> addLike(@NotBlank @RequestParam UUID postId, @NotBlank @RequestParam UUID memberId, BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }

        return createResponseEntity(
            ResponseCode.SUCCESS,
            likeService.add(postId, memberId)
        );
    }

    @DeleteMapping("/api/v1/like")
    public ResponseEntity<?> subLike(@NotBlank @RequestParam UUID postId, @NotBlank @RequestParam UUID memberId, BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        
        if (errors.size() != 0) {
            throw new CustomExceptions(ResponseCode.INVALID_PARAMETER, errors);
        }
        
        return createResponseEntity(
            ResponseCode.SUCCESS,
            likeService.subtract(postId, memberId)
        );
    }
}
