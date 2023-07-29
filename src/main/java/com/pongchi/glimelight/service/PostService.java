package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pongchi.glimelight.domain.post.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
    
    private final PostRepository postRepository;
}
