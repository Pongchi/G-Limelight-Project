package com.pongchi.glimelight.domain.post;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.pongchi.glimelight.domain.BaseTimeEntity;
import com.pongchi.glimelight.domain.comment.Comment;
// import com.pongchi.glimelight.domain.game.Game;
import com.pongchi.glimelight.domain.like.Like;
import com.pongchi.glimelight.domain.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "post_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    // @ManyToOne
    // @JoinColumn(name = "game_id")
    // private Game game;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 255)
    private String description;

    @Column(length = 255, nullable = false)
    private String videoUrl;

    @ColumnDefault("0")
    private long view_count;

    @OneToMany(mappedBy = "post" ,fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<Like>();

    private long like_count = 0;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    // @OneToMany(mappedBy = "post")
    // private ArrayList<PostHashTag> postHashTags;

    @Builder
    public Post(Member writer, String title, String description, String videoUrl) {
        this.writer = writer;
        // this.game = game;
        this.title = title;
        this.description = description;
        this.videoUrl = videoUrl;
    }

    @Override
    public boolean equals(Object obj) {
        Post other = (Post) obj;
        return this.id == other.id;
    }

    public void addLike(Like like) {
        likes.add(like);
        like_count++;
    }

    public void subLike(Like like) {
        likes.remove(like);
        like_count--;
    }
}
