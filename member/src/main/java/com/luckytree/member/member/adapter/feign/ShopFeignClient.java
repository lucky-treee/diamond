package com.luckytree.member.member.adapter.feign;

import com.luckytree.member.config.InternalFeignConfig;
import com.luckytree.member.member.adapter.data.bookmark.BookmarksResponse;
import com.luckytree.member.member.adapter.data.bookmark.FindBookmarkedShops;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shop-service", configuration = {InternalFeignConfig.class})
public interface ShopFeignClient {

    @PostMapping("/v1/shops/bookmarks")
    BookmarksResponse findBookmarksByIds(@RequestBody FindBookmarkedShops FindBookmarkedShops);
}
