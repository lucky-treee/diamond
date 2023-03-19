package com.luckytree.member_service.member.adapter.feign;

import com.luckytree.member_service.member.adapter.data.MyBookmarksDto;
import com.luckytree.member_service.member.adapter.data.ShopFeignRequestDto;
import com.luckytree.member_service.member.application.port.outgoing.ShopFeignClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ShopFeignClientAdapter implements ShopFeignClientPort {

    private final ShopFeignClient shopFeignClient;

    @Override
    public MyBookmarksDto findBookmarksByIds(List<Long> shopIds) {
        return shopFeignClient.findBookmarksByIds(new ShopFeignRequestDto(shopIds));
    }
}
