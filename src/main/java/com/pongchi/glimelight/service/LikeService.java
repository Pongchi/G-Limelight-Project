package com.pongchi.glimelight.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pongchi.glimelight.api.v1.dto.like.LikeCheckDto;
import com.pongchi.glimelight.domain.like.Like;
import com.pongchi.glimelight.domain.like.LikeRepository;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.post.Post;
import com.pongchi.glimelight.domain.post.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeService {
    
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    public LikeCheckDto check(UUID postId, UUID memberId) {
        Post post = postRepository.findById(postId).get();
        Member member = memberRepository.findById(memberId).get();

        Optional<Like> like = likeRepository.findByPostAndMember(post, member);

        return new LikeCheckDto(like.isEmpty() ? false : true);
    }

    public Like add(UUID postId, UUID memberId) {
        Post post = postRepository.findById(postId).get();
        Member member = memberRepository.findById(memberId).get();

        Like like = new Like(member, post);
        likeRepository.save(like);

        post.addLike(like);

        return like;
    }

    public Like subtract(UUID postId, UUID memberId) {
        Post post = postRepository.findById(postId).get();
        Member member = memberRepository.findById(memberId).get();

        Like like = likeRepository.findByPostAndMember(post, member).get();
        post.subLike(like);

        likeRepository.delete(like);

        return like;
    }
}
