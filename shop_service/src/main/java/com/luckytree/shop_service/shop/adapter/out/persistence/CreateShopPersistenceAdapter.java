package com.luckytree.shop_service.shop.adapter.out.persistence;

import com.luckytree.shop_service.shop.application.port.out.CreateShopPort;
import com.luckytree.shop_service.shop.application.port.in.ShopRequest;
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
