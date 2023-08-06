package com.pongchi.glimelight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.subscribe.SubscribeCheckDto;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.domain.subscribe.Subscribe;
import com.pongchi.glimelight.domain.subscribe.SubscribeRepository;

@Transactional
@SpringBootTest
public class SubscribeServiceTest {
    
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Test
    public void 구독_추가() {
        // given
        String fromEmail = "fromtest@email.com";
        String fromPassword = "fromtest";
        String fromNickname = "fromMember";
        Member fromMember = new Member(fromEmail, fromPassword, fromNickname);
        memberRepository.save(fromMember);

        String toEmail = "totest@email.com";
        String toPassword = "totest";
        String toNickname = "toMember";
        Member toMember = new Member(toEmail, toPassword, toNickname);
        memberRepository.save(toMember);

        // when
        long addedSubsribeId = subscribeService.addSubscribe(fromMember.getId(), toMember.getId());

        // then
        Subscribe subscribe = subscribeRepository.findById(addedSubsribeId).get();
        assertEquals(subscribe.getId(), addedSubsribeId);
        assertEquals(subscribe.getFromMember().getId().toString(), fromMember.getId().toString());
        assertEquals(subscribe.getToMember().getId().toString(), toMember.getId().toString());
    }

    @Test
    public void 멤버를_향한_구독_모두_찾기() {
        // given
        String fromEmail = "fromtest@email.com";
        String fromPassword = "fromtest";
        String fromNickname = "fromMember";
        Member fromMember = new Member(fromEmail, fromPassword, fromNickname);
        memberRepository.save(fromMember);

        String toEmail = "totest@email.com";
        String toPassword = "totest";
        String toNickname = "toMember";
        Member toMember = new Member(toEmail, toPassword, toNickname);
        memberRepository.save(toMember);

        long addedSubscribeId = subscribeService.addSubscribe(fromMember.getId(), toMember.getId());

        // when
        Page<Subscribe> subscribes = subscribeService.findAllByToMember(toMember.getId(), Pageable.ofSize(1));

        // then
        assertEquals(subscribes.getContent().get(0).getId(), addedSubscribeId);
    }

    @Test
    public void 구독_체크() {
        // given
        String fromEmail = "fromtest@email.com";
        String fromPassword = "fromtest";
        String fromNickname = "fromMember";
        Member fromMember = new Member(fromEmail, fromPassword, fromNickname);
        memberRepository.save(fromMember);

        String toEmail = "totest@email.com";
        String toPassword = "totest";
        String toNickname = "toMember";
        Member toMember = new Member(toEmail, toPassword, toNickname);
        memberRepository.save(toMember);

        subscribeService.addSubscribe(fromMember.getId(), toMember.getId());

        // when
        SubscribeCheckDto savedSubscribe = subscribeService.check(fromMember.getId(), toMember.getId());

        // then
        assertEquals(savedSubscribe.isCheck(), true);
    }
}
