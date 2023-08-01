package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.api.v1.dto.like.LikeCheckDto;
import com.pongchi.glimelight.domain.like.Like;
import com.pongchi.glimelight.service.LikeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LikeController {
    
    private final LikeService likeService;

    @GetMapping("/api/v1/like")
    public LikeCheckDto check(@RequestParam UUID postId, @RequestParam UUID memberId) {
        return likeService.check(postId, memberId);
    }
    
    @PostMapping("/api/v1/like")
    public Like addLike(@RequestParam UUID postId, @RequestParam UUID memberId) {
        return likeService.add(postId, memberId);
    }

    @DeleteMapping("/api/v1/like")
    public Like subLike(@RequestParam UUID postId, @RequestParam UUID memberId) {
        return likeService.subtract(postId, memberId);
    }
}
