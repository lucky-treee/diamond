package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.shop.adapter.jpa.ShopEntity;
import com.luckytree.shop_service.shop.domain.Shop;

import java.util.List;

public interface ShopPort {

    void createShop(Shop shop);

    List<ShopEntity> getShopSummaryByCategory(Category category);

    List<ShopEntity> getShopAll(double maxLat, double minLat, double maxLng, double minLng);

    List<ShopEntity> getShopSummaryByHashtag(Hashtag hashtag);

    ShopEntity getShopDetailById(long shopId);

    void deleteShop(ShopEntity shopEntity, String comment);

    ShopEntity getShopEntity(String name, String address);

    Category findCategoryById(long shopId);

    List<ShopEntity> findBookmarkDtosByIds(List<Long> shopIds);

    List<ShopEntity> findReviewDtosByIds(List<Long> shopIds);
}