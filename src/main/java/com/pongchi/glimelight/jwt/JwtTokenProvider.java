package com.pongchi.glimelight.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.access-secret-key}")
    private String JWT_ACCESS_SECRET_KEY;
    
    @Value("${jwt.token.refresh-secret-key}")
    private String JWT_REFRESH_SECRET_KEY;

    @Value("${jwt.token.access-expire-length}")
    private long ACCESS_EXPIRE_TIME;

    @Value("${jwt.token.refresh-expire-length}")
    private long REFRESH_EXPIRE_TIME;

    @Autowired
    private UserDetailsService userDetailsService;

    public String generateAccessToken(Authentication authentication) {

        Claims claims = Jwts.claims().setSubject(authentication.getName());

        Date now = new Date();
        Date expiresIn = new Date(now.getTime() + ACCESS_EXPIRE_TIME);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiresIn)
            .signWith(SignatureAlgorithm.HS256, JWT_ACCESS_SECRET_KEY)
            .compact();
    }

    public String generateRefreshToken(String email) {;
        Date now = new Date();
        Date expiresIn = new Date(now.getTime() + REFRESH_EXPIRE_TIME);

        return Jwts.builder()
            .claim("email", email)
            .setIssuedAt(now)
            .setExpiration(expiresIn)
            .signWith(SignatureAlgorithm.HS256, JWT_REFRESH_SECRET_KEY)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        String email = Jwts.parser().setSigningKey(JWT_ACCESS_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token, boolean isAccessToken) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(isAccessToken ? JWT_ACCESS_SECRET_KEY : JWT_REFRESH_SECRET_KEY)
                    .parseClaimsJws(token);

            return claims.getBody().getExpiration().before(new Date());
        } catch (JwtException e) {
            // MalformedJwtException | ExpiredJwtException | IllegalArgumentException
            throw new CustomException(ResponseCode.TOKEN_AUTHENTICATION_FAIL);
        }
    }
}