package com.luckytree.shop_service.shop.application.port.incoming;

import com.luckytree.shop_service.shop.adapter.data.Category;
import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopDetail;
import com.luckytree.shop_service.shop.domain.ShopSummary;

import java.util.List;

public interface ShopUseCase {

    void createShop(ShopRequest shopRequest);

    List<ShopSummary> findShopsByCategory(Category category);

    List<ShopSummary> findShopsByLatAndLng(double maxLat, double minLat, double maxLng, double minLng);

    List<ShopSummary> findShopsByHashtag(Hashtag hashtag);

    ShopDetail findShopByNameAndAddress(String name, String address);

    void deleteShop(RemoveRequestForm removeRequestForm);

    List<ShopDetail> findShopsByIds(List<Long> ids);
}
