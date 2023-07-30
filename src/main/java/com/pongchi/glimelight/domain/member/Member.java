package com.pongchi.glimelight.domain.member;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.pongchi.glimelight.domain.BaseTimeEntity;
import com.pongchi.glimelight.domain.comment.Comment;
import com.pongchi.glimelight.domain.post.Post;
import com.pongchi.glimelight.domain.subscribe.Subscribe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/*
    - id : UUID
    - password : String
    - role : Enum.ROLE
    - post : ArrayList<Post>  ( OneToMany)
    - subscribe : ArrayList<Subscribe> ( OneToMany )
    - subscribe_count : int
 */

@Getter
@ToString(exclude = "password")
@Entity
public class Member extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "member_id")
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(length = 32, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;

    @Column(length = 32)
    private String nickname;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private ArrayList<Post> posts;

    @OneToMany(mappedBy = "toMember", fetch = FetchType.LAZY)
    private List<Subscribe> mySubscribes = new ArrayList<>();
    private Long mySubscribes_count = 0L;

    @OneToMany(mappedBy = "fromMember", fetch = FetchType.LAZY)
    private List<Subscribe> otherSubscribes = new ArrayList<>();;
    private Long otherSubscribes_count = 0L;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private ArrayList<Comment> comments;

    @Builder
    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void addMySubscribe(Subscribe subscribe) {
        mySubscribes.add(subscribe);
        mySubscribes_count += 1L;
    }

    public void addOtherSubscribe(Subscribe subscribe) {
        otherSubscribes.add(subscribe);
        otherSubscribes_count += 1L;
    }
}
