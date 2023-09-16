package com.pongchi.glimelight.api.v1;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.domain.game.Game;
import com.pongchi.glimelight.service.GameService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GameController {
    
    private final GameService gameService;

    @GetMapping("/api/v1/game/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name") String name) {
        Game game = gameService.findByName(name);
        return createResponseEntity(
            ResponseCode.SUCCESS,
            game
        );
    }

    @PostMapping("/api/v1/game")
    public ResponseEntity<?> create(String name, String bannerUrl) {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            gameService.create(name, bannerUrl)
        );
    }
}
