package com.luckytree.member_service.common.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotAuthorizedException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Random;

@Slf4j
@NoArgsConstructor
@Component
public class TokenProvider implements InitializingBean {
    @Value("${jwt.secret}")
    String secret;
    @Value("${jwt.token-validity-in-seconds}")
    long tokenValidityInMilliseconds;
    @Value("${jwt.refresh-token.expire-length}")
    long refreshTokenValidityInMilliseconds;
    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    private String createToken(String payload, long tokenValidity) {
        Claims claims = Jwts.claims().setSubject(payload);

        long now = (new Date()).getTime();
        Date validity = new Date(now + tokenValidity);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public String createAccessToken(String payload) {
        return createToken(payload, tokenValidityInMilliseconds);
    }

    public String createRefreshToken() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        return createToken(generatedString, refreshTokenValidityInMilliseconds);
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new BadRequestException("????????? JWT ???????????????.");
        } catch (ExpiredJwtException e) {
            throw new NotAuthorizedException("????????? ???????????????.");
        } catch (UnsupportedJwtException e) {
            throw new BadRequestException("???????????? ?????? JWT ???????????????.");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("JWT ????????? ?????????????????????.");
        }
    }
}
