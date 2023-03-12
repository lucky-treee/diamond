package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.shop.adapter.data.Category;
import com.luckytree.shop_service.shop.application.port.incoming.GetShopListUseCase;
import com.luckytree.shop_service.shop.application.port.outgoing.GetShopPort;
import com.luckytree.shop_service.shop.domain.ShopDetail;
import com.luckytree.shop_service.shop.domain.Hashtag;
import com.luckytree.shop_service.shop.domain.ShopSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetShopListService implements GetShopListUseCase {

    private final GetShopPort getShopPort;

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummary> getShopSummaryByCategory(Category category) {
        return getShopPort.getShopSummaryByCategoryAndEnable(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummary> getShopAll(double maxLat, double minLat, double maxLng, double minLng) {
        return getShopPort.getShopAll(maxLat, minLat, maxLng, minLng);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummary> getShopSummaryByHashtag(Hashtag hashtag) {
        return getShopPort.getShopSummaryByHashtag(hashtag);
    }

    @Transactional(readOnly = true)
    @Override
    public ShopDetail getShopDetail(String name, String address) {
        return getShopPort.getShopDetail(name, address);
    }
}
