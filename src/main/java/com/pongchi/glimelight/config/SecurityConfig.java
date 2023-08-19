package com.pongchi.glimelight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
            .csrf((csrf) -> csrf
                .ignoringRequestMatchers(new AntPathRequestMatcher("/**")))
            .formLogin((formLogin) -> formLogin
                .permitAll()
                .defaultSuccessUrl("https://laughing-guacamole-6w4pp7wwv49h46r6-3000.app.github.dev/"))
            .logout((logout) -> logout
                .permitAll()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
				.clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/members/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true))
        ;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 