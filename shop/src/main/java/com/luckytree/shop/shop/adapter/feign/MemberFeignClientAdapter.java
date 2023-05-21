package com.luckytree.shop.shop.adapter.feign;

import com.luckytree.shop.common.enums.Category;
import com.luckytree.shop.shop.adapter.data.MemberFeignRequestDto;
import com.luckytree.shop.shop.application.port.outgoing.MemberFeignClientPort;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.enums.ShopCategory;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberFeignClientAdapter implements MemberFeignClientPort {

    private final MemberFeignClient memberFeignClient;

    @Override
    public void saveBookmark(long memberId, long shopId, ShopCategory category) {
        memberFeignClient.saveBookmark(new MemberFeignRequestDto(memberId, shopId, category));
    }
    @Override
    public void deleteBookmark(long memberId, long shopId){
        memberFeignClient.deleteBookmark(memberId, shopId);
    }
}