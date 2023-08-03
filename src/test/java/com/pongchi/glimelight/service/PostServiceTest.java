package com.pongchi.glimelight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pongchi.glimelight.api.v1.dto.post.PostCreateRequestDto;
import com.pongchi.glimelight.api.v1.dto.post.PostResponseDto;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.post.Post;
import com.pongchi.glimelight.domain.post.PostRepository;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class PostServiceTest {
    
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 게시글_작성() {
        // given
        String email = "test@email.com";
        String password = "password";
        String nickname = "nickname";
        Member member = new Member(email, password, nickname);
        memberRepository.save(member);

        String title = "title";
        String description = "description";
        String videoUrl = "videoUrl";
        PostCreateRequestDto requestDto = new PostCreateRequestDto(member.getId(), title, description, videoUrl);
        // when
        UUID postId = postService.create(requestDto);

        // then
        Post savedPost = postRepository.findById(postId).get();
        assertEquals(savedPost.getId().toString(), postId.toString());
        assertEquals(savedPost.getTitle(), title);
        assertEquals(savedPost.getDescription(), description);
        assertEquals(savedPost.getVideoUrl(), videoUrl);

        assertEquals(savedPost.getWriter().getId(), member.getId());
    }

    @Test
    public void 모든_게시글_찾기() {
        // given
        String email = "test@email.com";
        String password = "password";
        String nickname = "nickname";
        Member member = new Member(email, password, nickname);
        memberRepository.save(member);

        String title = "title";
        String description = "description";
        String videoUrl = "videoUrl";

        for (int i=0; i < 5; i++) {
            PostCreateRequestDto requestDto = new PostCreateRequestDto(member.getId(), title + String.valueOf(i), description, videoUrl);
            postService.create(requestDto);
        }

        // when
        int size = 3;
        Page<PostResponseDto> savedPosts = postService.findAll(Pageable.ofSize(size));

        // then
        assertEquals(savedPosts.getSize(), size);
        assertEquals(savedPosts.getContent().get(0).getDescroption(), description);
        assertEquals(savedPosts.getContent().get(0).getVideoUrl(), videoUrl);
    }

    @Test
    public void 게시글_읽기() {
        // given
        String email = "test@email.com";
        String password = "password";
        String nickname = "nickname";
        Member member = new Member(email, password, nickname);
        memberRepository.save(member);

        String title = "title";
        String description = "description";
        String videoUrl = "videoUrl";
        PostCreateRequestDto requestDto = new PostCreateRequestDto(member.getId(), title, description, videoUrl);
        UUID postId = postService.create(requestDto);

        // when
        PostResponseDto responseDto = postService.read(postId);

        // then
        assertEquals(responseDto.getId().toString(), postId.toString());
        assertEquals(responseDto.getTitle(), title);
        assertEquals(responseDto.getDescroption(), description);
        assertEquals(responseDto.getWriterId(), member.getId());
    }

    @Test
    public void 게시글_삭제() {
        // given
        String email = "test@email.com";
        String password = "password";
        String nickname = "nickname";
        Member member = new Member(email, password, nickname);
        memberRepository.save(member);

        String title = "title";
        String description = "description";
        String videoUrl = "videoUrl";
        PostCreateRequestDto requestDto = new PostCreateRequestDto(member.getId(), title, description, videoUrl);
        UUID postId = postService.create(requestDto);

        // when
        postService.delete(postId);

        // then
        assertEquals(postRepository.count(), 0);
    }
}