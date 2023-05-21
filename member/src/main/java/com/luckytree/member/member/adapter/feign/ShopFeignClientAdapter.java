package com.luckytree.member.member.adapter.feign;

import com.luckytree.member.member.adapter.data.BookmarksResponse;
import com.luckytree.member.member.adapter.data.FindBookmarkedShops;
import com.luckytree.member.member.application.port.outgoing.ShopFeignClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ShopFeignClientAdapter implements ShopFeignClientPort {

    private final ShopFeignClient shopFeignClient;

    @Override
    public BookmarksResponse findBookmarksByIds(FindBookmarkedShops findBookmarkedShops) {
        return shopFeignClient.findBookmarksByIds(findBookmarkedShops);
    }
}
