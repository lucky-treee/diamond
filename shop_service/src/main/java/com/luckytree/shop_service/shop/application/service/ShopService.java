package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.shop.adapter.data.ShopRequest;
import com.luckytree.shop_service.shop.adapter.persistence.ShopEntity;
import com.luckytree.shop_service.shop.application.port.incoming.RemoveRequestForm;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop_service.shop.application.port.outgoing.ShopPort;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopDetail;
import com.luckytree.shop_service.shop.domain.ShopSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService implements ShopUseCase {

    private final ShopPort shopPort;

    @Transactional
    @Override
    public void requestShopRegistration(ShopRequest shopRequest) {
        shopPort.saveShopWithDisable(shopRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummary> getShopSummaryByCategory(String category) {
        return shopPort.getShopSummaryByCategory(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummary> getShopAll(double maxLat, double minLat, double maxLng, double minLng) {
        return shopPort.getShopAll(maxLat, minLat, maxLng, minLng);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummary> getShopSummaryByHashtag(Hashtag hashtag) {
        return shopPort.getShopSummaryByHashtag(hashtag);
    }

    @Transactional(readOnly = true)
    @Override
    public ShopDetail getShopDetail(String name, String address) {
        return shopPort.getShopDetail(name, address);
    }

    @Transactional
    @Override
    public void removeShopRequest(RemoveRequestForm removeRequestForm) {
        ShopEntity shopEntity = shopPort.getShopEntity(removeRequestForm.getName(), removeRequestForm.getAddress());
        shopPort.saveRemoveRequest(shopEntity, removeRequestForm.getComment());
    }

    @Override
    public List<ShopDetail> getShopsByIds(List<Long> ids) {
        return null;
    }
}
