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

    ShopDetailDto findShopByNameAndAddress(String name, String address);

    void deleteShop(String name, String address, String comment);

    MyBookmarksDto findMyBookmarksDtoByIds(List<Long> ids);
}
