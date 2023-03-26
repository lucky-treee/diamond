package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.data.LoginDto;
import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.Tokens;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import com.luckytree.member_service.member.application.port.outgoing.TokenPort;
import com.luckytree.member_service.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@Service
public class AuthenticationService implements AuthenticationUseCase {

    private final AuthenticationPort authenticationPort;
    private final TokenPort tokenPort;


    @Override
    public void login(String authorization) {
        String accessToken = Objects.requireNonNull(authorization).substring(7);
        tokenPort.validateToken(accessToken);
    }

    @Override
    @Transactional
    public Tokens signup(SignupDto signUpDto) {
        long memberId = authenticationPort.saveMember(new Member(signUpDto));
        return issueTokens(memberId);
    }

    @Override
    public Tokens login(String code, String redirectUri) {
        String accessToken = authenticationPort.getUserKakaoAccessToken(code, redirectUri);
        String email = authenticationPort.getUserKakaoEmail(accessToken);
        long memberId = authenticationPort.findMemberIdByEmail(email);

        return issueTokens(memberId);
    }

    private Tokens issueTokens(long memberId) {
        String accessToken = tokenPort.createAccessToken(memberId);
        String refreshToken = tokenPort.createRefreshToken();

        return new Tokens(accessToken, refreshToken);
    }
}
