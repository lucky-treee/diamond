package com.luckytree.shop_service.shop.application.port.incoming;

import com.luckytree.shop_service.shop.adapter.data.ShopRequest;

public interface CreateShopUseCase {
    void requestShopRegistration(ShopRequest shopRequest);
}
