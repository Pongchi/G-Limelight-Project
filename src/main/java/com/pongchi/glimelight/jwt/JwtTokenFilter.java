package com.pongchi.glimelight.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.exception.CustomException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String accessToken = jwtTokenProvider.resolveToken(request);

        try {
            // Access Token 이 유효할 경우 통과
            if (accessToken != null) {
                if (jwtTokenProvider.validateToken(accessToken, true)) {
                    Authentication auth = jwtTokenProvider.getAuthentication(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
                else {
                    SecurityContextHolder.clearContext();
                    ResponseCode result = ResponseCode.TOKEN_EXPIRED;
                    response.sendError(result.getStatus(), result.getMessage());
                    return;
                }
            }
        } catch (CustomException e) {
            SecurityContextHolder.clearContext();
            response.sendError(e.getResponseCode().getStatus(), e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }
}