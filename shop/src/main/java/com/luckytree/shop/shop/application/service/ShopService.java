package com.luckytree.shop.shop.application.service;

import com.luckytree.shop.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop.shop.application.port.outgoing.ShopPort;
import com.luckytree.shop.shop.application.port.outgoing.ShopRemovePort;
import com.luckytree.shop.shop.domain.shop.SearchShopsCondition;
import com.luckytree.shop.shop.domain.shop.Shop;
import com.luckytree.shop.shop.domain.shop.ShopRemoveDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopService implements ShopUseCase {

    private final ShopPort shopPort;
    private final ShopRemovePort shopRemovePort;

    @Override
    public void create(Shop shop) {
        if (shop.valid(shop)) shopPort.save(shop);
    }

    @Transactional(readOnly = true)
    @Override
    public Shop getById(Long id) {
        return shopPort.findById(id);
    }

    @Override
    public List<Shop> getShops(SearchShopsCondition searchShopsCondition) {
        return shopPort.findAll(searchShopsCondition);
    }

    @Override
    public void delete(ShopRemoveDetail shopRemoveDetail) {
        shopRemovePort.saveShopRemove(shopRemoveDetail);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Shop> getShopsByIds(List<Long> shopIds) {
        return shopPort.findShopsByIds(shopIds);
    }
}