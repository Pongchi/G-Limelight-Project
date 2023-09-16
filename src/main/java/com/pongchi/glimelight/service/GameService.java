package com.pongchi.glimelight.service;

import org.springframework.stereotype.Service;

import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.domain.game.Game;
import com.pongchi.glimelight.domain.game.GameRepository;
import com.pongchi.glimelight.exception.CustomException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameService {
    
    private final GameRepository gameRepository;

    public Game findByName(String name) {
        Game game = gameRepository.findByName(name)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_GAME)
            );

        return game;
    }
}
