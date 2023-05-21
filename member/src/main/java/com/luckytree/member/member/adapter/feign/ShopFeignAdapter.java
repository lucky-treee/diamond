package com.luckytree.member.member.adapter.feign;

import com.luckytree.member.member.adapter.data.bookmark.BookmarksResponse;
import com.luckytree.member.member.adapter.data.bookmark.FindBookmarkedShops;
import com.luckytree.member.member.application.port.outgoing.ShopFeignPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ShopFeignAdapter implements ShopFeignPort {

    private final ShopFeignClient shopFeignClient;

    @Override
    public BookmarksResponse findBookmarksByIds(FindBookmarkedShops findBookmarkedShops) {
        return shopFeignClient.findBookmarksByIds(findBookmarkedShops);
    }
}
