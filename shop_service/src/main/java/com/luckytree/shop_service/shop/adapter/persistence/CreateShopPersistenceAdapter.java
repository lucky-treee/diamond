package com.luckytree.shop_service.shop.adapter.persistence;

import com.luckytree.shop_service.shop.application.port.outgoing.CreateShopPort;
import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CreateShopPersistenceAdapter implements CreateShopPort {

    private final ShopRepository shopRepository;

    @Override
    public void saveShopWithDisable(ShopRequest shopRequest) {
        shopRepository.save(new ShopEntity(shopRequest));
    }
}
