package com.luckytree.member_service.common.jwt;


import com.luckytree.member_service.common.advice.BadRequestException;
import com.luckytree.member_service.common.advice.UnAuthorizedException;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

@Slf4j
@NoArgsConstructor
@Component
public class TokenProvider {
    @Value("${jwt.secret}")
    String secret;
    @Value("${jwt.token-validity-in-seconds}")
    long tokenValidityInMilliseconds;
    @Value("${jwt.refresh-token.expire-length}")
    long refreshTokenValidityInMilliseconds;

    private String generate(String payload, long tokenValidity) {
        Claims claims = Jwts.claims().setSubject(payload);

        long now = (new Date()).getTime();
        Date validity = new Date(now + tokenValidity);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(validity)
                .compact();
    }

    public String generateAccessToken(String payload) {
        return generate(payload, tokenValidityInMilliseconds);
    }

    public String generateRefreshToken() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        return generate(generatedString, refreshTokenValidityInMilliseconds);
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
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
        validateToken(token);
        String memberId = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();

        return Long.parseLong(memberId);
    }
}
