package com.pongchi.glimelight.api.v1;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.domain.subscribe.Subscribe;
import com.pongchi.glimelight.service.SubscribeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubscribeController {

    private final SubscribeService subscribeService;
    
    @GetMapping("/api/v1/subscribe")
    public Page<Subscribe> findAllByToMember(@RequestParam UUID toMemberId, Pageable pageable) {
        return subscribeService.findAllByToMember(toMemberId, pageable);
    }

    @PostMapping("/api/v1/subscribe")
    public long addSubscribe(@RequestBody UUID from, @RequestBody UUID to) {
        return subscribeService.addSubscribe(from ,to);
    }
}
