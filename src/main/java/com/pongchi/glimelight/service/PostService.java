package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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

    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(post -> PostResponseDto.builder()
                .id(post.getId())
                .writerId(post.getWriter().getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .videoUrl(post.getVideoUrl()).build());

    }

    @Transactional(readOnly = true)
    public PostResponseDto read(UUID id) {
        Post post = postRepository.findById(id).get();
        return PostResponseDto.builder()
                .id(id)
                .writerId(post.getWriter().getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .videoUrl(post.getVideoUrl())
                .build();
    }

    public PostResponseDto delete(UUID id) {
        Post post = postRepository.findById(id).get();

        postRepository.delete(post);

        return PostResponseDto.builder()
                .id(id)
                .writerId(post.getWriter().getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .videoUrl(post.getVideoUrl())
                .build();
    }

}
