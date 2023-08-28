package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.like.LikeCheckDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.domain.like.Like;
import com.pongchi.glimelight.domain.like.LikeRepository;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.post.Post;
import com.pongchi.glimelight.domain.post.PostRepository;
import com.pongchi.glimelight.exception.CustomException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeService {
    
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    @Transactional(readOnly = true)
    public LikeCheckDto check(UUID postId, UUID memberId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_POST)
            );
        Member member = memberRepository.findById(memberId)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_MEMBER)
            );

        return new LikeCheckDto( likeRepository.findByPostAndMember(post, member) );
    }

    public Like add(UUID postId, UUID memberId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_POST)
            );
        Member member = memberRepository.findById(memberId)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_MEMBER)
            );

        Like like = new Like(member, post);
        likeRepository.save(like);

        post.addLike(like);

        return like;
    }

    public Like subtract(UUID postId, UUID memberId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_POST)
            );
        Member member = memberRepository.findById(memberId)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_MEMBER)
            );

        Like like = likeRepository.findByPostAndMember(post, member)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_LIKE)
            );

        post.subLike(like);

        likeRepository.delete(like);

        return like;
    }
}
