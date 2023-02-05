package com.luckytree.shop_service.shop.application.port.out;

import com.luckytree.shop_service.shop.application.port.in.ShopRequest;

public interface CreateShopPort {

    void saveShopWithDisable(ShopRequest shopRequest);
}
