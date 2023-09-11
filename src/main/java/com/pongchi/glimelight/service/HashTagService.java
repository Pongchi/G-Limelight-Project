package com.pongchi.glimelight.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pongchi.glimelight.domain.post.Hashtag;
import com.pongchi.glimelight.domain.post.HashtagRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HashTagService {

    private final HashtagRepository hashtagRepository;

    @Transactional
    public Hashtag findByTag(String tag) {
        Optional<Hashtag> hashtag = hashtagRepository.findByTag(tag);
        
        if (hashtag.isEmpty()) {
            Hashtag newHashtag = new Hashtag(tag);
            return hashtagRepository.save(newHashtag);
        } else {
            return hashtag.get();
        }
    }
    
}
