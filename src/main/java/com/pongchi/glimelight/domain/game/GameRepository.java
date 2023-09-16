package com.pongchi.glimelight.domain.game;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    
    public Optional<Game> findByName(String name);

    public boolean existsByName(String name);
}
