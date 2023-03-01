package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.persistence.ShopEntity;

public interface RemoveShopPort {

    void saveRemoveRequest(ShopEntity shopEntity, String comment);

    ShopEntity getShopEntity(String name, String address);
}
