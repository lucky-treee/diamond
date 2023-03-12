package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.data.Category;
import com.luckytree.shop_service.shop.domain.ShopDetail;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopSummary;

import java.util.List;

public interface GetShopPort {

     List<ShopSummary> getShopSummaryByCategoryAndEnable(Category category);

     List<ShopSummary> getShopAll(double maxLat, double minLat, double maxLng, double minLng);

     List<ShopSummary> getShopSummaryByHashtag(Hashtag hashtag);

     ShopDetail getShopDetail(String name, String address);
}
