package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.data.*;
import com.luckytree.member_service.member.adapter.feign.KakaoTokenFeignClient;
import com.luckytree.member_service.member.adapter.feign.KakaoUserInfoFeignClient;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import com.luckytree.member_service.member.application.port.outgoing.TokenPort;
import com.luckytree.member_service.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService implements AuthenticationUseCase {

    private final AuthenticationPort authenticationPort;
    private final TokenPort tokenPort;


    @Override
    public void login(LoginDto loginDto) {
        tokenPort.validateToken(loginDto.getAccessToken());
    }

    @Override
    @Transactional
    public Tokens signup(SignupDto signUpDto) {
        long memberId = authenticationPort.saveMember(new Member(signUpDto));
        return issueTokens(memberId);
    }

    @Override
    public Tokens login(String code) {
        String accessToken = authenticationPort.getUserKakaoAccessToken(code);
        String email = authenticationPort.getUserKakaoEmail(accessToken);
        long memberId = authenticationPort.findMemberIdByEmail(email);

        return issueTokens(memberId);
    }

    private Tokens issueTokens(long userId) {
        String accessToken = tokenPort.createAccessToken(userId);
        String refreshToken = tokenPort.createRefreshToken();

        return new Tokens(accessToken, refreshToken);
    }
}
