package com.luckytree.shop_service.shop.adapter.persistence;

import com.luckytree.shop_service.shop.application.port.outgoing.RemoveShopPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

@RequiredArgsConstructor
@Repository
public class RemoveShopPersistenceAdapter implements RemoveShopPort {

    private final ShopRemoveRepository shopRemoveRepository;

    private final ShopRepository shopRepository;

    @Override
    public void saveRemoveRequest(ShopEntity shopEntity, String comment) {
        shopRemoveRepository.save(new ShopRemoveEntity(shopEntity, comment));
    }

    @Override
    public ShopEntity getShopEntity(String name, String address) {
       return shopRepository.findByNameAndAddress(name, address).orElseThrow(() -> new NotFoundException("해당 shopName과 address애 일치하는 ShopEntity가 없습니다. name: " + name + ", address: " + address));
    }
}
