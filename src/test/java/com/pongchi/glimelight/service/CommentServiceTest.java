package com.pongchi.glimelight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.comment.CommentCreationDto;
import com.pongchi.glimelight.api.v1.dto.comment.CommentOnPostRequestDto;
import com.pongchi.glimelight.domain.comment.Comment;
import com.pongchi.glimelight.domain.comment.CommentRepository;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.post.Post;
import com.pongchi.glimelight.domain.post.PostRepository;

@Transactional
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void 코멘트_생성() {
        // given
        Member member = new Member("test", "test", "test");
        memberRepository.save(member);

        Post post = new Post(member, "test", "test", "test");
        postRepository.save(post);

        String commentMessage = "commentCreate";
        CommentCreationDto creationDto = new CommentCreationDto(member.getId(), post.getId(), commentMessage);

        // when
        Comment savedComment = commentService.create(creationDto);

        // then
        assertEquals(savedComment.getMember().getId().toString(), member.getId().toString());
        assertEquals(savedComment.getPost().getId().toString(), post.getId().toString());
        assertEquals(savedComment.getMessage(), commentMessage);
    }
    
    @Test
    public void 게시글_코멘트_불러오기() {
        // given
        Member member = new Member("test", "test", "test");
        memberRepository.save(member);

        Post post = new Post(member, "test", "test", "test");
        postRepository.save(post);

        String commentMessage = "commentRead";
        CommentCreationDto creationDto = new CommentCreationDto(member.getId(), post.getId(), commentMessage);
        commentService.create(creationDto);

        CommentOnPostRequestDto requestDto = new CommentOnPostRequestDto(post.getId());

        // then
        List<Comment> comments = commentService.getCommentsOnPost(requestDto);

        // then
        assertEquals(comments.size(), 1);
        assertEquals(comments.get(0).getMember().getId().toString(), member.getId().toString());
        assertEquals(comments.get(0).getPost().getId().toString(), post.getId().toString());
        assertEquals(comments.get(0).getMessage(), commentMessage);
    }

    @Test
    public void 코멘트_삭제() {
        // given
        Member member = new Member("test", "test", "test");
        memberRepository.save(member);

        Post post = new Post(member, "test", "test", "test");
        postRepository.save(post);

        String commentMessage = "commentRead";
        CommentCreationDto creationDto = new CommentCreationDto(member.getId(), post.getId(), commentMessage);
        Comment comment = commentService.create(creationDto);

        // when
        commentService.delete(comment.getId());

        // then
        assertEquals(commentRepository.findById(comment.getId()).isEmpty(), true);
    }
    
}
