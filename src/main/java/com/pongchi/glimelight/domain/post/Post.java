package com.pongchi.glimelight.domain.post;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.pongchi.glimelight.domain.game.Game;
import com.pongchi.glimelight.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

/*
    - id : long
    - writer: User
    - game : Game ( ManyToOne )
    - title : String
    - description: String
    - video_url : String
    - view_count : int
    - like : Like (OneToMany)
    - like_count : int
    - hashtag : String ( ManyToMany)
    - comment : Comment ( OneToMany)
    - created_at : LocalDateTime
    - modified_at : LocalDateTime 
 */
@Getter
@Entity
public class Post {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "post_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 255)
    private String description;

    @Column(length = 255, nullable = false)
    private String videoUrl;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
