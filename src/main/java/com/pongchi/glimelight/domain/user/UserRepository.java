package com.pongchi.glimelight.domain.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, UUID> {
    
    @Query("select u from User u where u.email = :email and u.password = :password")
    Optional<User> login(@Param("email") String email, @Param("password") String password);

    Optional<User> findById(UUID id);
}
