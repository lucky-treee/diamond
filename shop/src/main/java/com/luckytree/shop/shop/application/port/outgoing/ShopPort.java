package com.luckytree.shop.shop.application.port.outgoing;

import com.luckytree.shop.shop.domain.shop.SearchShopsCondition;
import com.luckytree.shop.shop.domain.shop.Shop;

import java.util.List;

public interface ShopPort {

    void save(Shop shop);

    Shop findById(Long shopId);

    List<Shop> findAll(SearchShopsCondition searchShopsCondition);

    List<Shop> findShopsByIds(List<Long> shopIds);
}