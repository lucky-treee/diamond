package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.data.ShopRequest;

public interface CreateShopPort {

    void saveShopWithDisable(ShopRequest shopRequest);
}
