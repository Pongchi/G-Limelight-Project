package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.service.LikeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LikeController {
    
    private final LikeService likeService;

    @GetMapping("/api/v1/like")
    public ResponseEntity<?> check(@RequestParam UUID postId, @RequestParam UUID memberId) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            likeService.check(postId, memberId)
        );
    }
    
    @PostMapping("/api/v1/like")
    public ResponseEntity<?> addLike(@RequestParam UUID postId, @RequestParam UUID memberId) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            likeService.add(postId, memberId)
        );
    }

    @DeleteMapping("/api/v1/like")
    public ResponseEntity<?> subLike(@RequestParam UUID postId, @RequestParam UUID memberId) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            likeService.subtract(postId, memberId)
        );
    }
}
