package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.shop.application.port.in.CreateShopUseCase;
import com.luckytree.shop_service.shop.application.port.in.ShopRequest;
import com.luckytree.shop_service.shop.application.port.out.CreateShopPort;
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

