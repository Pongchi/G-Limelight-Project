package com.pongchi.glimelight.domain.post;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Post, Long>{
    
    Optional<Hashtag> findByTag(String tag);

	Hashtag save(Hashtag newHashtag);
}
