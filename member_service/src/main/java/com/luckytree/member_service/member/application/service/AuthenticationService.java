package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.common.jwt.TokenProvider;
import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.TokenDto;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import com.luckytree.member_service.member.adapter.data.LoginDto;
import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import com.luckytree.member_service.member.domain.Member;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.NoContentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements AuthenticationUseCase {

    private final AuthenticationPort authenticationPort;

    private final TokenProvider tokenProvider;

    @Override
    public void login(LoginDto loginDto) {
        // 1. 전달받은 인가코드로 카카오 서버에 토큰 요청

        // 2. 받은 토큰으로 카카오 서버에 리소스 요청

        // 3. 얻은 리소스와 일치하는 회원이 있는지 DB 조회
        String email = "test@gmail.com";
        boolean isMember = authenticationPort.existsByEmail(email); // 더미
        if (!isMember) {
            throw new NotFoundException("일치하는 유저가 없습니다.");
        }
        tokenProvider.validateToken(loginDto.getAccessToken());
    }

    @Override
    @Transactional
    public TokenDto signup(SignupDto signUpDto) {
        String email = authenticationPort.saveMember(new Member(signUpDto));
        String accessToken = tokenProvider.createAccessToken(email);
        String refreshToken = tokenProvider.createRefreshToken();
        return new TokenDto(accessToken, refreshToken);
    }
}
