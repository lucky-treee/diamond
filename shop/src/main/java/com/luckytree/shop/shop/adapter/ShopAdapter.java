package com.luckytree.shop.shop.adapter;

import com.luckytree.shop.shop.adapter.jpa.shop.ShopEntity;
import com.luckytree.shop.shop.adapter.jpa.shop.ShopRemoveEntity;
import com.luckytree.shop.shop.adapter.jpa.shop.ShopRemoveRepository;
import com.luckytree.shop.shop.adapter.jpa.shop.ShopRepository;
import com.luckytree.shop.shop.application.port.outgoing.ShopPort;
import com.luckytree.shop.shop.domain.shop.Shop;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;
import luckytree.poom.core.enums.ShopStatus;
import luckytree.poom.core.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ShopAdapter implements ShopPort {

    private final ShopRepository shopRepository;
    private final ShopRemoveRepository shopRemoveRepository;

    @Override
    public void createShop(Shop shop) {
        shopRepository.save(new ShopEntity(shop));
    }

    @Override
    public List<ShopEntity> getShopSummaryByCategory(ShopCategory category) {
        return shopRepository.findByCategoryAndStatus(category, ShopStatus.ENABLE);
    }

    @Override
    public List<ShopEntity> getShopAll(double maxLat, double minLat, double maxLng, double minLng) {
        return shopRepository.findByStatusAndLatLessThanEqualAndLatGreaterThanEqualAndLngLessThanEqualAndLngGreaterThanEqual(ShopStatus.ENABLE, maxLat, minLat, maxLng, minLng);
    }

    @Override
    public ShopEntity getShopDetailById(long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("해당 shopId와 일치하는 ShopEntity가 없습니다. shopID: " + shopId));
    }

    @Override
    public List<ShopEntity> getShopSummaryByHashtag(ShopHashtag hashtag) {
        return shopRepository.findByHashtagAndStatus(hashtag, ShopStatus.ENABLE);
    }

    @Override
    public void deleteShop(ShopEntity shopEntity, String comment) {
        shopRemoveRepository.save(new ShopRemoveEntity(shopEntity, comment));
    }

    @Override
    public ShopEntity getShopEntity(String name, String address) {
        return shopRepository.findByNameAndAddress(name, address).orElseThrow(() -> new NotFoundException("해당 shopName과 address애 일치하는 ShopEntity가 없습니다. name: " + name + ", address: " + address));
    }

    @Override
    public ShopCategory findCategoryById(long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("해당 shopId에 일치하는 ShopEntity가 없습니다. id: " + shopId)).getCategory();
    }

    @Override
    public List<ShopEntity> findBookmarkDtosByIds(List<Long> shopIds) {
        return shopRepository.findAllByIdIn(shopIds);
    }
}