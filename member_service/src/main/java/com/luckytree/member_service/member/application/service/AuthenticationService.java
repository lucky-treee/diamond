package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.common.jwt.TokenProvider;
import com.luckytree.member_service.member.adapter.data.*;
import com.luckytree.member_service.member.adapter.feign.KakaoTokenFeignClient;
import com.luckytree.member_service.member.adapter.feign.KakaoUserInfoFeignClient;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import com.luckytree.member_service.member.domain.Member;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements AuthenticationUseCase {

    private final AuthenticationPort authenticationPort;
    private final TokenProvider tokenProvider;
    private final KakaoTokenFeignClient kakaoTokenFeignClient;
    private final KakaoUserInfoFeignClient kakaoUserInfoFeignClient;

    @Value("${oauth2.kakao.clientId}")
    String clientId;
    @Value("${oauth2.kakao.secretKey}")
    String clientSecret;
    @Value("${oauth2.kakao.redirectUri}")
    String redirectUri;

    @Override
    public void login(LoginDto loginDto) {
        tokenProvider.validateToken(loginDto.getAccessToken());
    }

    @Override
    @Transactional
    public TokenDto signup(SignupDto signUpDto) {
        String email = authenticationPort.saveMember(new Member(signUpDto));
        return issueTokens(email);
    }

    @Override
    public TokenDto login(String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoTokenFeignClient.getToken(new KakaoTokenRequest(code, clientId, clientSecret, redirectUri).toString());
        KakaoUserInfo kakaoUserInfo = kakaoUserInfoFeignClient.getUser(kakaoTokenResponse.getAccessToken());
        String email = kakaoUserInfo.getKakaoAccount().getEmail();

        isMember(email);

        return issueTokens(email);
    }

    private void isMember(String email) {
        if(!authenticationPort.existsByEmail(email)) {
            throw new NotFoundException("없는 유저입니다. email= " + email);
        }
    }

    private TokenDto issueTokens(String email) {
        String accessToken = tokenProvider.createAccessToken(email);
        String refreshToken = tokenProvider.createRefreshToken();

        return new TokenDto(accessToken, refreshToken);
    }
}
