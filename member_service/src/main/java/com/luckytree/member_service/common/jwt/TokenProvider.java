package com.luckytree.member_service.common.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Random;

@Slf4j
@Component
public class TokenProvider {
    private final long tokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds,
                         @Value("${jwt.refresh-token.expire-length}") long refreshTokenValidityInMilliseconds) {

        byte[] keyBytes = Decoders.BASE64.decode(secret);

        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
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

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
