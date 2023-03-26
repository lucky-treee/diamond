package com.luckytree.member_service.member.adapter.redis;

import com.luckytree.member_service.member.application.port.outgoing.RedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RedisAdapter implements RedisPort {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void save(String memberId, String refreshToken) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set(memberId, refreshToken);
    }
}
