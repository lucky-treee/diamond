package com.luckytree.shop_service.shop.application.port.out;

import com.luckytree.shop_service.shop.adapter.out.persistence.ShopEntity;
import com.luckytree.shop_service.shop.domain.ShopDetail;

public interface RemoveShopPort {

    void saveRemoveRequest(ShopEntity shopEntity, String comment);

    ShopEntity getShopEntity(String name, String address);
}
