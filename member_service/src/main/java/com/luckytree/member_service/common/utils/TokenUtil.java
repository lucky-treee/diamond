package com.luckytree.member_service.common.utils;

import com.luckytree.member_service.common.advice.BadRequestException;
import com.luckytree.member_service.common.advice.UnAuthorizedException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public class TokenUtil {

    @Value("${jwt.secret}")
    static String secret;

    public static Long parseMemberId(String authorization) {
        String accessToken = Objects.requireNonNull(authorization).substring(7);
        validate(accessToken);
        String memberId = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(accessToken).getBody().getSubject();

        return Long.parseLong(memberId);
    }

    public static void validate(String token) {
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
}
