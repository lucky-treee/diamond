package com.luckytree.shop_service.shop.application.port.incoming;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.data.*;
import com.luckytree.shop_service.common.enums.Hashtag;

import java.util.List;

public interface ShopUseCase {

    void createShop(CreateShopDto createShopDto);

    List<ShopSummaryDto> findShopsByCategory(Category category);

    List<ShopSummaryDto> findShopsByLatAndLng(double maxLat, double minLat, double maxLng, double minLng);

    List<ShopSummaryDto> findShopsByHashtag(Hashtag hashtag);

    ShopDetailDto findShopById(long shopId);

    void deleteShop(String name, String address, String comment);

    void createBookmark(long memberId, long shopId);

    MyBookmarksDto findMyBookmarksDtoByIds(List<Long> ids);

    void deleteBookmark(long memberId, long shopId);
}
