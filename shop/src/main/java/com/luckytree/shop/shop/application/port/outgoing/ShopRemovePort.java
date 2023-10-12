package com.luckytree.shop.shop.application.port.outgoing;

import com.luckytree.shop.shop.domain.shop.ShopRemoveDetail;

public interface ShopRemovePort {
    void saveShopRemove(ShopRemoveDetail shopRemoveDetail);
}
