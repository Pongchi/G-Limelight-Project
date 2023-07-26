package com.pongchi.glimelight.domain.user;

import java.util.ArrayList;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.pongchi.glimelight.domain.post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

/*
    - id : UUID
    - password : String
    - role : Enum.ROLE
    - post : ArrayList<Post>  ( OneToMany)
    - subscribe : ArrayList<Subscribe> ( OneToMany )
    - subscribe_count : int
 */

@Getter
@Entity
public class User {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "user_id")
    private UUID id;

    @Column(length = 32, nullable = false)
    private String password;

    private Role role;

    @OneToMany(mappedBy = "writer")
    private ArrayList<Post> posts;

    @OneToMany(mappedBy = "toUser")
    private ArrayList<Subscribe> mySubscribes;
    private long mySubscribes_count;

    @OneToMany(mappedBy = "fromUser")
    private ArrayList<Subscribe> otherSubscribes;
    private long otherSubscribes_count;
}
