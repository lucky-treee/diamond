package com.luckytree.member_service.common.annotation;

import com.luckytree.member_service.common.advice.NotFoundException;
import com.luckytree.member_service.common.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@Component
public class AuthorizationArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;

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
        return tokenProvider.getMemberIdByDecoding(accessToken);
    }
}
