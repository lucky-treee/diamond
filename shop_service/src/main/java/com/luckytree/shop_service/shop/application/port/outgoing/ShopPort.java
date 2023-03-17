package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.data.BookmarkDto;
import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.data.CreateShopDto;
import com.luckytree.shop_service.shop.adapter.jpa.ShopEntity;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.shop.adapter.data.ShopDetail;
import com.luckytree.shop_service.shop.adapter.data.ShopSummary;
import com.luckytree.shop_service.shop.domain.Shop;

import java.util.List;

public interface ShopPort {

    void createShop(Shop shop);

    List<ShopSummary> getShopSummaryByCategory(Category category);

    List<ShopSummary> getShopAll(double maxLat, double minLat, double maxLng, double minLng);

    List<ShopSummary> getShopSummaryByHashtag(Hashtag hashtag);

    ShopDetail getShopDetail(String name, String address);

    void saveRemoveRequest(ShopEntity shopEntity, String comment);

    ShopEntity getShopEntity(String name, String address);

    List<BookmarkDto> findBookmarkDtosByIds(List<Long> shopIds);
}
