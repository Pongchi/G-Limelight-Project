package com.pongchi.glimelight.domain.subscribe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pongchi.glimelight.domain.member.Member;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    
    Page<Subscribe> findAllByToMember(Member toMember, Pageable pageable);
}