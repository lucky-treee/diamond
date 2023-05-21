package com.luckytree.shop.shop.application.port.incoming;

import com.luckytree.shop.shop.adapter.data.*;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;

import java.util.List;

public interface ShopUseCase {

    void createShop(CreateShopDto createShopDto);

    List<ShopSummaryDto> findShopsByCategory(ShopCategory category);

    List<ShopSummaryDto> findShopsByLatAndLng(ShopSearchDto shopSearchDto);

    List<ShopSummaryDto> findShopsByHashtag(ShopHashtag hashtag);

    ShopDetailDto findShopById(long shopId);

    void deleteShop(String authorization, ShopDeleteDto shopDeleteDto);

    void createBookmark(String authorization, long shopId);

    MyBookmarksDto findMyBookmarksDtoByIds(List<Long> ids);

    void deleteBookmark(String authorization, long shopId);
}
