package com.luckytree.member_service.member.adapter.redis;

import com.luckytree.member_service.common.advice.BadRequestException;
import com.luckytree.member_service.member.application.port.outgoing.RedisPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RedisAdapter implements RedisPort {

    private final RedisTemplate redisTemplate;

    @Override
    public void save(Long memberId, String refreshToken) {
        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken, memberId);
    }

    @Override
    public Long findById(String refreshToken) {
        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        Long memberId = valueOperations.get(refreshToken);

        if (memberId == null) {
            throw new BadRequestException("잘못된 토큰입니다.");
        }

        return memberId;
    }
}
