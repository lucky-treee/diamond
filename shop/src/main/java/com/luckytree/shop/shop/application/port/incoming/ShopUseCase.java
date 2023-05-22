package com.luckytree.shop.shop.application.port.incoming;

import com.luckytree.shop.shop.adapter.data.bookmark.MyBookmarksResponse;
import com.luckytree.shop.shop.adapter.data.shop.*;
import com.luckytree.shop.shop.domain.shop.DeleteShop;
import com.luckytree.shop.shop.domain.shop.Shop;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;

import java.util.List;

public interface ShopUseCase {

    void createShop(Shop shop);

    List<ShopSummaryResponse> findShopsByCategory(ShopCategory category);

    List<ShopSummaryResponse> findShopsByLatAndLng(SearchShopRequest searchShopRequest);

    List<ShopSummaryResponse> findShopsByHashtag(ShopHashtag hashtag);

    ShopDetailResponse findShopById(Long shopId);

    void deleteShop(String authorization, DeleteShop deleteShop);

    MyBookmarksResponse findMyBookmarksResponseByIds(List<Long> ids);
}
