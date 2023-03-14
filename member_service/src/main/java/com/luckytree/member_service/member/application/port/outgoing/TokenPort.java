package com.luckytree.member_service.member.application.port.outgoing;

public interface TokenPort {

    void validateToken(String token);
    String createAccessToken(long userId);
    String createRefreshToken();
}
