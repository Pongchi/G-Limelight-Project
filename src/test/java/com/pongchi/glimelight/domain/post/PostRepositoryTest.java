package com.pongchi.glimelight.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void 게시글_작성() {
        // given
        Member writer = new Member("test@test.com", "test", "test");
        memberRepository.save(writer);

        Post post = new Post(writer, "test", "test", "http://test.test");

        // when
        Post savedPost = postRepository.save(post);

        // then
        assertThat(savedPost).isSameAs(
            postRepository.findById(savedPost.getId()).get()
        );
    }

    @Test
    public void 전체_게시글_불러오기() {
        // given
        Member writer = new Member("test@test.com", "test", "test");
        memberRepository.save(writer);

        for (int i=0; i < 100; i++) {
            Post post = new Post(writer, String.valueOf(i), "test", "http://test.test");
            postRepository.save(post);
        }
        
        // when
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> postList = postRepository.findAll(pageable);

        // then
        assertThat(postList.getContent().size()).isEqualTo(10);
        // postList.forEach(System.out::println);
    }

    @Test
    public void 게시글_하나_불러오기() {
        // given
        Member writer = new Member("test@test.com", "test", "test");
        memberRepository.save(writer);

        Post post = new Post(writer, "test", "test", "http://test.test");
        Post savedPost = postRepository.save(post);

        // when
        Post findedPost = postRepository.findById(savedPost.getId()).get();

        // then
        assertThat(findedPost).isEqualTo(findedPost);
    }

    @Test
    public void 게시글_삭제() {
        // given
        Member writer = new Member("test@test.com", "test", "test");
        memberRepository.save(writer);

        Post post = new Post(writer, "test", "test", "http://test.test");
        Post savedPost = postRepository.save(post);

        // when
        postRepository.delete(savedPost);
        List<Post> findedPosts = postRepository.findAll();

        // then
        assertThat(findedPosts.size()).isEqualTo(0);
    }

    // @Test
    // public void 게시글_해시태그_넣기() {

    // }
    
}
