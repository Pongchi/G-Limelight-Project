package com.pongchi.glimelight.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.domain.post.Hashtag;
import com.pongchi.glimelight.domain.post.HashtagRepository;
import com.pongchi.glimelight.exception.CustomException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HashTagService {

    private final HashtagRepository hashtagRepository;

    @Transactional(readOnly = true)
    public boolean existsByTag(String tag) {
        return hashtagRepository.existsByTag(tag);
    }

    @Transactional
    public Hashtag create(String tag) {
        if (existsByTag(tag)) throw new CustomException(ResponseCode.EXISTSHASHTAG);

        Hashtag hashtag = new Hashtag(tag);
        hashtagRepository.save(hashtag);
        return hashtag;
    }
    
}
