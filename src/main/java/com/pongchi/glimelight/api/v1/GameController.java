package com.pongchi.glimelight.api.v1;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.domain.game.Game;
import com.pongchi.glimelight.service.GameService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GameController {
    
    private final GameService gameService;

    public ResponseEntity<?> findByName(String name) {
        Game game = gameService.findByName(name);
        return createResponseEntity(
            ResponseCode.SUCCESS,
            game
        );
    }
}
