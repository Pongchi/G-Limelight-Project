package com.pongchi.glimelight.domain.subscribe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class SubscribeRepositoryTest {
    
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Test
    public void 단반향_구독() {
        // given
        Member fromMember = new Member("fromTest@test.com", "test", "test");
        Member toMember = new Member("toTest@test.com", "test", "test");
        memberRepository.save(fromMember);
        memberRepository.save(toMember);

        Subscribe subscribe = new Subscribe(fromMember, toMember);
        subscribeRepository.save(subscribe);

        fromMember.addMySubscribe(subscribe);
        toMember.addOtherSubscribe(subscribe);


        // then
        Member savedFromMember = memberRepository.findById(fromMember.getId()).get();
        Member savedToMember = memberRepository.findById(toMember.getId()).get();
        Subscribe savedSubscribe = subscribeRepository.findAll().get(0);

        assertThat(savedFromMember.getMySubscribes_count()).isEqualTo(1L);
        assertThat(savedToMember.getOtherSubscribes_count()).isEqualTo(1L);
        assertThat(savedSubscribe.getFromMember().getId()).isEqualTo(fromMember.getId());
        assertThat(savedSubscribe.getToMember().getId()).isEqualTo(toMember.getId());
    }

    @Test
    public void 양반향_구독() {
        // given
        Member fromMember = new Member("fromTest@test.com", "test", "test");
        Member toMember = new Member("toTest@test.com", "test", "test");
        memberRepository.save(fromMember);
        memberRepository.save(toMember);

        Subscribe subscribe1 = new Subscribe(fromMember, toMember);
        Subscribe subscribe2 = new Subscribe(toMember, fromMember);
        subscribeRepository.save(subscribe1);
        subscribeRepository.save(subscribe2);

        fromMember.addMySubscribe(subscribe1);
        toMember.addOtherSubscribe(subscribe1);

        toMember.addMySubscribe(subscribe2);
        fromMember.addOtherSubscribe(subscribe2);


        // then
        Member savedFromMember = memberRepository.findById(fromMember.getId()).get();
        Member savedToMember = memberRepository.findById(toMember.getId()).get();
        Subscribe savedSubscribe = subscribeRepository.findAll().get(1);

        assertThat(savedFromMember.getMySubscribes_count()).isEqualTo(1L);
        assertThat(savedToMember.getOtherSubscribes_count()).isEqualTo(1L);
        assertThat(savedSubscribe.getFromMember().getId()).isEqualTo(fromMember.getId());
        assertThat(savedSubscribe.getToMember().getId()).isEqualTo(toMember.getId());
    }

    @Test
    public void 구독체크() {
        // given
        Member fromMember = new Member("fromTest@test.com", "test", "test");
        Member toMember = new Member("toTest@test.com", "test", "test");
        memberRepository.save(fromMember);
        memberRepository.save(toMember);

        Subscribe subscribe = new Subscribe(fromMember, toMember);
        subscribeRepository.save(subscribe);

        // when
        Subscribe savedSubscribe = subscribeRepository.findByFromMemberAndToMember(fromMember, toMember).get();

        // then
        assertThat(savedSubscribe.getFromMember().getId()).inBinary().isEqualTo(fromMember.getId());
        assertThat(savedSubscribe.getToMember().getId()).inBinary().isEqualTo(toMember.getId());
    }
}
