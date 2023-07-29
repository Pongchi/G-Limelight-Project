package com.pongchi.glimelight.domain.member;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pongchi.glimelight.api.v1.dto.MemberRegisterRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@Transactional
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    
    @Test
    public void 멤버_회원가입() {
        // given
        String email = "test@email.com";
        String password = "test";
        String nickname = "test";
        MemberRegisterRequestDto requestDto = new MemberRegisterRequestDto(email, password, nickname);

        // when
        UUID newMemberId = memberRepository.save(requestDto.toEntity()).getId();

        System.out.println("[유저_회원가입] 회원가입된 유저 ID : " + newMemberId );

        // then
        assertThat(newMemberId).isNotNull();
    }
}
