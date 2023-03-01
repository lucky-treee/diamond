package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import com.luckytree.shop_service.shop.adapter.persistence.ShopEntity;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopDetail;
import com.luckytree.shop_service.shop.domain.ShopSummary;

import java.util.List;

public interface ShopPort {

    void saveShopWithDisable(ShopRequest shopRequest);

    List<ShopSummary> getShopSummaryByCategory(String category);

    List<ShopSummary> getShopAll(double maxLat, double minLat, double maxLng, double minLng);

    List<ShopSummary> getShopSummaryByHashtag(Hashtag hashtag);

    ShopDetail getShopDetail(String name, String address);

    void saveRemoveRequest(ShopEntity shopEntity, String comment);

    ShopEntity getShopEntity(String name, String address);
}