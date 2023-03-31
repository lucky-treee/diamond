package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.data.SignupRequest;
import com.luckytree.member_service.member.adapter.data.Tokens;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import com.luckytree.member_service.member.application.port.outgoing.RedisPort;
import com.luckytree.member_service.member.application.port.outgoing.TokenPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@Service
public class AuthenticationService implements AuthenticationUseCase {

    private final AuthenticationPort authenticationPort;
    private final TokenPort tokenPort;
    private final RedisPort redisPort;


    @Override
    public void login(String authorization) {
        String accessToken = Objects.requireNonNull(authorization).substring(7);
        tokenPort.validateToken(accessToken);
    }

    @Override
    @Transactional
    public Tokens signup(SignupRequest signUpRequest) {
        long memberId = authenticationPort.saveMember(signUpRequest);
        return issueTokens(memberId);
    }

    @Override
    public Tokens login(String code, String redirectUri) {
        String accessToken = authenticationPort.getUserKakaoAccessToken(code, redirectUri);
        String email = authenticationPort.getUserKakaoEmail(accessToken);
        long memberId = authenticationPort.findMemberIdByEmail(email);

        return issueTokens(memberId);
    }

    @Override
    public String makeCookie(String refreshToken) {
        ResponseCookie responseCookie = ResponseCookie
                .from("refresh-token", refreshToken)
                .domain("c0dewave.com")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .maxAge(60)
                .build();
        return responseCookie.toString();
    }

    @Override
    public Tokens reissue(String refreshToken) {
        tokenPort.validateToken(refreshToken);
        long memberId = redisPort.findById(refreshToken);

        return issueTokens(memberId);
    }

    private Tokens issueTokens(long memberId) {
        String accessToken = tokenPort.generateAccessToken(memberId);
        String refreshToken = tokenPort.generateRefreshToken();

        saveRefreshTokenToRedis(refreshToken, memberId);

        return new Tokens(accessToken, refreshToken);
    }

    private void saveRefreshTokenToRedis(String refreshToken, long memberId) {
        redisPort.save(refreshToken, memberId);
    }
}
