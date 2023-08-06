package com.pongchi.glimelight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.like.LikeCheckDto;
import com.pongchi.glimelight.domain.like.Like;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.post.Post;
import com.pongchi.glimelight.domain.post.PostRepository;

@Transactional
@SpringBootTest
public class LikeServiceTest {
    
    @Autowired
    private LikeService likeService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void 게시글_좋아요_추가() {
        // given
        Member writer = new Member("writer", "writer", "writer");
        Member liker = new Member("liker", "liker", "liker");
        memberRepository.save(writer);
        memberRepository.save(liker);

        Post post = new Post(writer, "post", "post", "url");
        postRepository.save(post);

        // when
        Like addedLike = likeService.add(post.getId(), liker.getId());

        // then
        assertEquals(addedLike.getPost().getId(), post.getId());
        assertEquals(addedLike.getPost().getLike_count(), 1L);
        assertEquals(addedLike.getPost().getLikes().size(), 1L);
        assertEquals(addedLike.getMember().getId(), liker.getId());
    }

    @Test
    public void 게시글_좋아요_삭제() {
        // given
        Member writer = new Member("writer", "writer", "writer");
        Member liker = new Member("liker", "liker", "liker");
        memberRepository.save(writer);
        memberRepository.save(liker);

        Post post = new Post(writer, "post", "post", "url");
        postRepository.save(post);

        likeService.add(post.getId(), liker.getId());

        // when
        Like subtractedLike = likeService.subtract(post.getId(), liker.getId());

        // then
        assertEquals(subtractedLike.getId(), subtractedLike.getId());
        assertEquals(subtractedLike.getPost().getId(), post.getId());
        assertEquals(subtractedLike.getPost().getLike_count(), 0L);
        assertEquals(subtractedLike.getPost().getLikes().size(), 0L);
        assertEquals(subtractedLike.getMember().getId(), liker.getId());
    }

    @Test
    public void 게시글_좋아요_확인_onEnable() {
        // given
        Member writer = new Member("writer", "writer", "writer");
        Member liker = new Member("liker", "liker", "liker");
        memberRepository.save(writer);
        memberRepository.save(liker);

        Post post = new Post(writer, "post", "post", "url");
        postRepository.save(post);

        Like addedLike = likeService.add(post.getId(), liker.getId());

        // when
        LikeCheckDto likeCheckDto = likeService.check(post.getId(), liker.getId());

        // then
        assertEquals(addedLike.getId(), 1L);
        assertEquals(addedLike.getPost().getId(), post.getId());
        assertEquals(addedLike.getPost().getLike_count(), 1L);
        assertEquals(addedLike.getPost().getLikes().size(), 1L);
        assertEquals(addedLike.getMember().getId(), liker.getId());
        assertEquals(likeCheckDto.isResult(), true);
    }

    @Test
    public void 게시글_좋아요_확인_onDisable() {
        // given
        Member writer = new Member("writer", "writer", "writer");
        Member liker = new Member("liker", "liker", "liker");
        memberRepository.save(writer);
        memberRepository.save(liker);

        Post post = new Post(writer, "post", "post", "url");
        postRepository.save(post);

        // when
        LikeCheckDto likeCheckDto = likeService.check(post.getId(), liker.getId());

        // then
        assertEquals(likeCheckDto.isResult(), false);
    }
}
