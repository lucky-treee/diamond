package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.data.BookmarksResponse;
import com.luckytree.member_service.member.adapter.data.FindBookmarkedShops;
import com.luckytree.member_service.member.adapter.data.ReviewsResponse;


public interface ShopFeignClientPort {

    BookmarksResponse findBookmarksByIds(FindBookmarkedShops FindBookmarkedShops);
    ReviewsResponse findShopMyReviewsById(long memberId);
}
