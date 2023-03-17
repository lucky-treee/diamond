package com.luckytree.shop_service.shop.application.port.incoming;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.data.MyBookmarksDto;
import com.luckytree.shop_service.shop.adapter.data.CreateShopDto;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.shop.adapter.data.ShopDetail;
import com.luckytree.shop_service.shop.adapter.data.ShopSummary;

import java.util.List;

public interface ShopUseCase {

    void createShop(CreateShopDto createShopDto);

    List<ShopSummary> findShopsByCategory(Category category);

    List<ShopSummary> findShopsByLatAndLng(double maxLat, double minLat, double maxLng, double minLng);

    List<ShopSummary> findShopsByHashtag(Hashtag hashtag);

    ShopDetail findShopByNameAndAddress(String name, String address);

    void deleteShop(RemoveRequestForm removeRequestForm);

    MyBookmarksDto findMyBookmarksDtoByIds(List<Long> ids);
}
