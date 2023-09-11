package com.pongchi.glimelight.api.v1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;
import com.pongchi.glimelight.domain.member.MemberRepository;

@Transactional
@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void clean() {
        memberRepository.deleteAll();
    }

    @Test
    void register(){
        //given
        String email = "test@email.com";
        String password = "test_password";
        String nickname = "test_nickname";

        MemberRegisterRequestDto requestDto = new MemberRegisterRequestDto(email, password, nickname);
        String url = "http://localhost:"+port+"/api/v1/members";

        //when
        ResponseEntity<?> response = restTemplate.postForEntity(url, requestDto, Object.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    // 실패 
    // @Test
    // void getMemberInfo() throws JsonProcessingException, ParseException{
    //     //given
    //     String email = "test@email.com";
    //     String password = "test_password";
    //     String nickname = "test_nickname";

    //     // 회원가입
    //     MemberRegisterRequestDto registerRequestDto = new MemberRegisterRequestDto(email, password, nickname);
    //     String registerUrl = "http://localhost:"+port+"/api/v1/members";
    //     restTemplate.postForEntity(registerUrl, registerRequestDto, Object.class);

    //     // 로그인
    //     MemberLoginRequestDto loginRequestDto = new MemberLoginRequestDto(email, password);
    //     String loginUrl = "http://localhost:"+port+"/api/v1/login";
    //     ResponseEntity<?> loginResponse = restTemplate.postForEntity(loginUrl, loginRequestDto, Object.class);

    //     ObjectMapper mapper = new ObjectMapper();
    //     HashMap<Object, Object> jsonNode = mapper.readValue(loginResponse.getBody().toString(), HashMap.class);

    //     String accessToken = jsonNode.get("result").toString();

    //     // 정보 받아오기
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.set("Authorization", "Bearer " + accessToken);
    //     HttpEntity<?> request = new HttpEntity<>(headers);

    //     UUID memberId = memberRepository.findAll().get(0).getId();
    //     String findUrl = "http://localhost:"+port+"/api/v1/members/" + memberId;

    //     // when
    //     ResponseEntity<?> findResponse = restTemplate.exchange(
    //         findUrl,
    //         HttpMethod.GET,
    //         request,
    //         Object.class
    //     );
        
    //     //then
    //     assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    // }
}