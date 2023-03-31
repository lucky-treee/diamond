package com.luckytree.member_service.member.application.port.outgoing;

public interface RedisPort {

    void save(String refreshToken, Long memberId);
    Long findById(String refreshToken);
}
