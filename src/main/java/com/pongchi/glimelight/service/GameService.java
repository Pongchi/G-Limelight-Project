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

    public boolean existsByName(String name) {
        return gameRepository.existsByName(name);
    }

    public Game create(String name, String bannerUrl) {
        if (existsByName(name)) throw new CustomException(ResponseCode.EXISTSGAME);

        Game game = new Game(name, bannerUrl);
        gameRepository.save(game);
        return game;
    }
}
