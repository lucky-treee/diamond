package com.luckytree.member_service.member.adapter.feign;

import com.luckytree.member_service.common.config.FeignClientConfig;
import com.luckytree.member_service.member.adapter.data.BookmarksResponse;
import com.luckytree.member_service.member.adapter.data.FindBookmarkedShops;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shop-service", configuration = {FeignClientConfig.class})
public interface ShopFeignClient {

    @PostMapping("/v1/bookmarks/shops")
    BookmarksResponse findBookmarksByIds(@RequestBody FindBookmarkedShops FindBookmarkedShops);
}
