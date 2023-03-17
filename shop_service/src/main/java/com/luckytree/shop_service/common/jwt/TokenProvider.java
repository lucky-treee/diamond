package com.luckytree.shop_service.common.jwt;


import com.luckytree.shop_service.common.exceptions.BadRequestException;
import com.luckytree.shop_service.common.exceptions.UnAuthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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
            throw new BadRequestException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new UnAuthorizedException("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new BadRequestException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("JWT 토큰이 잘못되었습니다.");
        } catch (MissingClaimException e) {
            throw new BadRequestException("JWT 토큰의 페이로드가 없습니다.");
        }
    }

    public long getMemberIdByDecoding(String token) {
        String memberId = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();

        return Long.parseLong(memberId);
    }
}
