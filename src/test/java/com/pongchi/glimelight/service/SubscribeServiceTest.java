package com.pongchi.glimelight.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.domain.member.MemberRepository;
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
    public void 멤버를_향한_구독_모두_찾기() {
        
    }
}
