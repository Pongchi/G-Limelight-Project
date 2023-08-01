package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.subscribe.SubscribeCheckDto;
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

        fromMember.addMySubscribe(subscribe);
        toMember.addOtherSubscribe(subscribe);

        return subscribe.getId();
    }

    @Transactional
    public long subSubscribe(UUID fromMemberId, UUID toMemberId) {
        Member fromMember = memberRepository.findById(fromMemberId).get();
        Member toMember = memberRepository.findById(toMemberId).get();

        Subscribe subscribe = subscribeRepository.findByFromMemberAndToMember(fromMember, toMember).get();

        fromMember.subMySubscribe(subscribe);
        toMember.subOtherSubscribe(subscribe);

        subscribeRepository.delete(subscribe);

        return subscribe.getId();
    }

    @Transactional(readOnly = true)
    public SubscribeCheckDto check(UUID fromMemberId, UUID toMemberId) {
        Member fromMember = memberRepository.findById(fromMemberId).get();
        Member toMember = memberRepository.findById(toMemberId).get();

        Subscribe subscribe = subscribeRepository.findByFromMemberAndToMember(fromMember, toMember).orElse(null);

        return new SubscribeCheckDto( subscribe != null ? true : false );
    }

}
