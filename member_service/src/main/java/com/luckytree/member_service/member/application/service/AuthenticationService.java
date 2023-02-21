package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.common.jwt.TokenProvider;
import com.luckytree.member_service.member.adapter.data.AuthenticateResult;
import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.TokenDto;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import com.luckytree.member_service.member.domain.Authentication;
import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import com.luckytree.member_service.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements AuthenticationUseCase {

    private final AuthenticationPort authenticationPort;

    private final TokenProvider tokenProvider;

    @Override
    public AuthenticateResult login(Authentication authentication) {
        // 1. 전달받은 인가코드로 카카오 서버에 토큰 요청

        // 2. 받은 토큰으로 카카오 서버에 리소스 요청

        // 3. 얻은 리소스와 일치하는 회원이 있는지 DB 조회
        String email = "test@gmail.com";
        boolean isMember = authenticationPort.existsByEmail(email); // 더미
        // 여기서 이메일 리턴
        return new AuthenticateResult(email, isMember);
        // 4-1. 없다면 받은 리소스(이메일) 저장(회원가입) -> 클라이언트에서 요청하면 될듯

        // 4-2. 있다면 품의 액세스 토큰을 내려준다. -> 로그인 과정인데 이것도 클라에서 요청

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
