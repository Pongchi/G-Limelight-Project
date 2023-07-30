package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pongchi.glimelight.api.v1.dto.post.PostCreateRequestDto;
import com.pongchi.glimelight.api.v1.dto.post.PostResponseDto;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.post.Post;
import com.pongchi.glimelight.domain.post.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
    
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public UUID create(PostCreateRequestDto requestDto) {
        Member writer = memberRepository.findById(requestDto.getWriterId()).get();
        Post post = Post.builder()
                        .writer(writer)
                        .title(requestDto.getTitle())
                        .description(requestDto.getDescription())
                        .videoUrl(requestDto.getVideoUrl())
                        .build(); 

        postRepository.save(post);
        return post.getId();
    }

    public Page<PostResponseDto> findAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(post -> PostResponseDto.builder()
                .id(post.getId())
                .writerId(post.getWriter().getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .videoUrl(post.getVideoUrl()).build());

    }
}
