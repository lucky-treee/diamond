package com.luckytree.shop.shop.adapter;

import com.luckytree.shop.common.enums.Category;
import com.luckytree.shop.common.enums.Hashtag;
import com.luckytree.shop.common.enums.ShopStatus;
import com.luckytree.shop.common.exceptions.NotFoundException;
import com.luckytree.shop.shop.adapter.jpa.ShopEntity;
import com.luckytree.shop.shop.adapter.jpa.ShopRemoveEntity;
import com.luckytree.shop.shop.adapter.jpa.ShopRemoveRepository;
import com.luckytree.shop.shop.adapter.jpa.ShopRepository;
import com.luckytree.shop.shop.application.port.outgoing.ShopPort;
import com.luckytree.shop.shop.domain.Shop;
import lombok.RequiredArgsConstructor;
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
    public List<ShopEntity> getShopSummaryByCategory(Category category) {
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
    public List<ShopEntity> getShopSummaryByHashtag(Hashtag hashtag) {
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
    public Category findCategoryById(long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("해당 shopId에 일치하는 ShopEntity가 없습니다. id: " + shopId)).getCategory();
    }

    @Override
    public List<ShopEntity> findBookmarkDtosByIds(List<Long> shopIds) {
        return shopRepository.findAllByIdIn(shopIds);
    }
}