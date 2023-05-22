package com.luckytree.shop.shop.application.port.outgoing;

import com.luckytree.shop.shop.adapter.jpa.shop.ShopEntity;
import com.luckytree.shop.shop.domain.shop.Shop;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;

import java.util.List;

public interface ShopPort {

    void createShop(Shop shop);

    List<ShopEntity> getShopSummaryByCategory(ShopCategory category);

    List<ShopEntity> getShopAll(double maxLat, double minLat, double maxLng, double minLng);

    List<ShopEntity> getShopSummaryByHashtag(ShopHashtag hashtag);

    ShopEntity getShopDetailById(Long shopId);

    void deleteShop(ShopEntity shopEntity, String comment);

    ShopEntity getShopEntity(String name, String address);

    List<ShopEntity> findBookmarksByIds(List<Long> shopIds);
}