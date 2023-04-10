package com.luckytree.member_service.common.annotation;

import com.luckytree.member_service.common.advice.BadRequestException;
import com.luckytree.member_service.common.advice.NotFoundException;
import com.luckytree.member_service.common.advice.UnAuthorizedException;
import com.luckytree.member_service.common.jwt.TokenProvider;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Log4j2
@NoArgsConstructor
@Component
public class AuthorizationArgumentResolver implements HandlerMethodArgumentResolver {

    @Value("${jwt.secret}")
    String secret;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMemberId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorization = webRequest.getHeader("Authorization");
        String accessToken;

        log.info("member auth :: {}", authorization);
        try {
            accessToken = Objects.requireNonNull(authorization).substring(7);
        } catch (NullPointerException e) {
            throw new NotFoundException("accessToken 이 없습니다.");
        }

        log.info("member accessToken :: {}", accessToken);
        return getMemberIdByDecoding(accessToken);
    }

    private Long getMemberIdByDecoding(String token) {
        validateToken(token);
        String memberId = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
        log.info("member id :: {}", memberId);
        return Long.parseLong(memberId);
    }

    private void validateToken(String token) {
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
