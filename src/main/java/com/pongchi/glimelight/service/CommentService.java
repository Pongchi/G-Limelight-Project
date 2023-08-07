package com.pongchi.glimelight.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.comment.CommentCreationDto;
import com.pongchi.glimelight.api.v1.dto.comment.CommentOnPostRequestDto;
import com.pongchi.glimelight.domain.comment.Comment;
import com.pongchi.glimelight.domain.comment.CommentRepository;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.post.Post;
import com.pongchi.glimelight.domain.post.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<Comment> getCommentsOnPost(CommentOnPostRequestDto requestDto) {
        return commentRepository.findAllByPostId(requestDto.getPostId());
    }

    @Transactional
    public Comment create(CommentCreationDto requestDto) {
        Post post = postRepository.findById(requestDto.getPostId()).get();
        Member member = memberRepository.findById(requestDto.getMemberId()).get();

        Comment comment = Comment.builder()
                .member(member)
                .post(post)
                .message(requestDto.getMessage())
                .build();
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment delete(UUID id) {
        Comment comment = commentRepository.findById(id).get();
        commentRepository.delete(comment);
        return comment;
    }
}
