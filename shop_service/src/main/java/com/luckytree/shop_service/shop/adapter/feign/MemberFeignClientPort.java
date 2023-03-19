package com.luckytree.shop_service.shop.adapter.feign;

public interface MemberFeignClientPort {

    void deleteBookmark(long memberId, long shopId);
}
