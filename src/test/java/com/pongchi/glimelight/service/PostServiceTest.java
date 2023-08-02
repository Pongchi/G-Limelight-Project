package com.pongchi.glimelight.service;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pongchi.glimelight.api.v1.dto.post.PostCreateRequestDto;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.post.Post;
import com.pongchi.glimelight.domain.post.PostRepository;

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
    }
}
