package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.shop.adapter.persistence.ShopEntity;
import com.luckytree.shop_service.shop.application.port.incoming.RemoveRequestForm;
import com.luckytree.shop_service.shop.application.port.incoming.RemoveShopUseCase;
import com.luckytree.shop_service.shop.application.port.outgoing.RemoveShopPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoveShopService implements RemoveShopUseCase {

    private final RemoveShopPort removeShopPort;

    @Transactional
    @Override
    public void removeShopRequest(RemoveRequestForm removeRequestForm) {
        ShopEntity shopEntity = removeShopPort.getShopEntity(removeRequestForm.getName(), removeRequestForm.getAddress());
        removeShopPort.saveRemoveRequest(shopEntity, removeRequestForm.getComment());
    }
}
