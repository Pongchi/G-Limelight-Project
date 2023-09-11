package com.pongchi.glimelight.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>{
    
    boolean existsByTag(String tag);
}
