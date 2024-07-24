package com.rumahsakit.project.project.rumahsakit.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.*;

@Component
public class JwtTokenUtil implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    // generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        log.info("roles={}", roles);
        if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            log.info("put isAdmin");
            claims.put("isAdmin", true);
        }
//        if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
//            claims.put("isUser", true);
//        }
//        if (roles.contains(new SimpleGrantedAuthority("ROLE_MODERATOR"))) {
//            claims.put("isModerator", true);
//        }
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }

    public String getUsernameFromToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token);

        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        Boolean isAdmin = claims.getBody().get("isAdmin", Boolean.class);
//        Boolean isUser = claims.getBody().get("isUser", Boolean.class);
//        Boolean isModerator = claims.getBody().get("isModerator", Boolean.class);
        log.info("claims: {isAdmin}: {}",isAdmin); //, isUser, isModerator

        if (isAdmin != null && isAdmin) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            log.info("admin?" + isAdmin);
        }
//        if (isUser != null && isUser) {
//            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
//        if (isModerator != null && isModerator) {
//            roles.add(new SimpleGrantedAuthority("ROLE_MODERATOR"));
//        }

        return roles;

    }

    private Key key() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}