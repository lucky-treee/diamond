package com.luckytree.member_service.member.adapter.token;

import com.luckytree.member_service.common.jwt.TokenProvider;
import com.luckytree.member_service.member.application.port.outgoing.TokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenAdapter implements TokenPort {

    private final TokenProvider tokenProvider;

    @Override
    public void validateToken(String token) {
        tokenProvider.validateToken(token);
    }

    @Override
    public String generateAccessToken(long memberId) {
        return tokenProvider.generateAccessToken(String.valueOf(memberId));
    }

    @Override
    public String generateRefreshToken() {
        return tokenProvider.generateRefreshToken();
    }

    @Override
    public long getClaims(String refreshToken) {
        return tokenProvider.getMemberIdByDecoding(refreshToken);
    }
}
