package com.luckytree.shop.shop.application.port.incoming;

import com.luckytree.shop.shop.domain.shop.SearchShopsCondition;
import com.luckytree.shop.shop.domain.shop.Shop;
import com.luckytree.shop.shop.domain.shop.ShopRemoveDetail;

import java.util.List;

public interface ShopUseCase {

    void create(Shop shop);

    Shop getById(Long id);

    List<Shop> getShops(SearchShopsCondition searchShopsCondition);

    void delete(ShopRemoveDetail shopRemoveDetail);

    List<Shop> getShopsByIds(List<Long> ids);
}
