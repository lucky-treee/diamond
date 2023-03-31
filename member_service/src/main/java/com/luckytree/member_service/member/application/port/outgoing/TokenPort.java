package com.luckytree.member_service.member.application.port.outgoing;

public interface TokenPort {

    void validateToken(String token);
    String generateAccessToken(long memberId);
    String generateRefreshToken();
    long getClaims(String refreshToken);
}
