package com.pongchi.glimelight.domain.member;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    
    @Query("select m from Member m where m.email = :email and m.password = :password")
    Optional<Member> login(@Param("email") String email, @Param("password") String password);

    Optional<Member> findById(UUID id);
}
