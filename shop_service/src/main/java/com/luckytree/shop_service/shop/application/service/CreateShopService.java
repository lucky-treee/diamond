package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.shop.application.port.incoming.CreateShopUseCase;
import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import com.luckytree.shop_service.shop.application.port.outgoing.CreateShopPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateShopService implements CreateShopUseCase {

    private final CreateShopPort createShopPort;

    @Transactional
    @Override
    public void requestShopRegistration(ShopRequest shopRequest) {
        createShopPort.saveShopWithDisable(shopRequest);
    }

}

