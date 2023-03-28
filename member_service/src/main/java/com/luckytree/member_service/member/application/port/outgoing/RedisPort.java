package com.luckytree.member_service.member.application.port.outgoing;

public interface RedisPort {

    void save(Long memberId, String refreshToken);
    Long findById(String refreshToken);
}
