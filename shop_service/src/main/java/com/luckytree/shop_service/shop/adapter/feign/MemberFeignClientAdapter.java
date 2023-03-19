package com.luckytree.shop_service.shop.adapter.feign;

import com.luckytree.shop_service.shop.adapter.data.MemberFeignRequestDto;
import com.luckytree.shop_service.shop.application.port.outgoing.MemberFeignClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberFeignClientAdapter implements MemberFeignClientPort {

    private final MemberFeignClient memberFeignClient;

    @Override
    public void saveBookmark(MemberFeignRequestDto memberFeignRequestDto) {
        memberFeignClient.saveBookmark(memberFeignRequestDto);
    }
}