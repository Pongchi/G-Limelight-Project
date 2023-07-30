package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.subscribe.Subscribe;
import com.pongchi.glimelight.domain.subscribe.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    
    private final MemberRepository memberRepository;
    private final SubscribeRepository subscribeRepository;

    @Transactional(readOnly = true)
    public Page<Subscribe> findAllByToMember(UUID toMemberId, Pageable pageable) {
        Member toMember = memberRepository.findById(toMemberId).get();
        return subscribeRepository.findAllByToMember(toMember, pageable);
    }

    @Transactional
    public long addSubscribe(UUID fromMemberId, UUID toMemberId) {
        Member fromMember = memberRepository.findById(fromMemberId).get();
        Member toMember = memberRepository.findById(toMemberId).get();

        Subscribe subscribe = new Subscribe(fromMember, toMember);
        subscribeRepository.save(subscribe);

        return subscribe.getId();
    }

}
