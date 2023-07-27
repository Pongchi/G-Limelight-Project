package com.pongchi.glimelight.domain.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UUID, User> {
    
}
