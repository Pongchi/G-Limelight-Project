package com.pongchi.glimelight.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pongchi.glimelight.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    //권한 확인을 하지 않는 uri
    private static final AntPathRequestMatcher[] PERMIT_ALL_PATTERNS = new AntPathRequestMatcher[] {
        AntPathRequestMatcher.antMatcher("/api/v1/members"),
        AntPathRequestMatcher.antMatcher("/api/v1/members/login"),
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable csrf to use token
        http
            .csrf().disable();

        // No session will be created or used by spring security
        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(PERMIT_ALL_PATTERNS)
                    .permitAll()
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/api/**"))
                    .authenticated()
        );

        // Apply JWT
        http.apply(new JwtSecurityConfig(jwtTokenProvider));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 