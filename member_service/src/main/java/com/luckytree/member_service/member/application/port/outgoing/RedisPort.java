package com.luckytree.member_service.member.application.port.outgoing;

public interface RedisPort {

    void save(String memberId, String refreshToken);
}
