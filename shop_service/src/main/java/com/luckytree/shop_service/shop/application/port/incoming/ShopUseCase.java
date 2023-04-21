package com.luckytree.shop_service.shop.application.port.incoming;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.data.*;
import com.luckytree.shop_service.common.enums.Hashtag;

import java.util.List;

public interface ShopUseCase {

    void createShop(CreateShopDto createShopDto);

    List<ShopSummaryDto> findShopsByCategory(Category category);

    List<ShopSummaryDto> findShopsByLatAndLng(ShopSearchDto shopSearchDto);

    List<ShopSummaryDto> findShopsByHashtag(Hashtag hashtag);

    ShopDetailDto findShopById(long shopId);

    void deleteShop(String authorization, ShopDeleteDto shopDeleteDto);

    void createBookmark(String authorization, long shopId);

    MyBookmarksDto findMyBookmarksDtoByIds(List<Long> ids);

    void deleteBookmark(String authorization, long shopId);

    List<ShopReviewDto> getShopReviews(String authorization, long shopId);

    MyReviewsDto findMyReviewsById(long memberId);
}
