package com.luckytree.shop_service.shop.adapter.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberFeignClientAdapter implements MemberFeignClientPort {

    private final MemberFeignClient memberFeignClient;

    @Override
    public void deleteBookmark(long memberId, long shopId){
        memberFeignClient.deleteBookmark(memberId, shopId);
    }
}
