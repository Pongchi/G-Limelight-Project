package com.pongchi.glimelight.domain.like;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.post.Post;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
    Optional<Like> findByPostAndMember(Post post, Member member);
}
