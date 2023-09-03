package com.pongchi.glimelight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pongchi.glimelight.jwt.CustomAccessDeniedHandler;
import com.pongchi.glimelight.jwt.JwtAuthenticationEntryPoint;
import com.pongchi.glimelight.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    //권한 확인을 하지 않는 uri
    private static final AntPathRequestMatcher[] PERMIT_ALL_PATTERNS = new AntPathRequestMatcher[] {
        AntPathRequestMatcher.antMatcher("/api/v1/members"),
        AntPathRequestMatcher.antMatcher("/api/v1/members/login"),
        AntPathRequestMatcher.antMatcher("/api/v1/exception/**"),
        AntPathRequestMatcher.antMatcher("/test"),
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable csrf to use token
        http
            .csrf(CsrfConfigurer::disable);

        // No session will be created or used by spring security
        http
        .sessionManagement(configurer -> configurer
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        
        http
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(PERMIT_ALL_PATTERNS)
                    .permitAll()
                    .anyRequest().authenticated()
        );

        http
            .exceptionHandling(
                authenticationManager -> authenticationManager
                            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                            .accessDeniedHandler(accessDeniedHandler)
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