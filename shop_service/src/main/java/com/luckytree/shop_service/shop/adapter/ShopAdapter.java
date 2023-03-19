package com.luckytree.shop_service.shop.adapter;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.common.enums.ShopStatus;
import com.luckytree.shop_service.shop.adapter.jpa.ShopEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ShopRemoveEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ShopRemoveRepository;
import com.luckytree.shop_service.shop.adapter.jpa.ShopRepository;
import com.luckytree.shop_service.shop.application.port.outgoing.ShopPort;
import com.luckytree.shop_service.shop.domain.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

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
    public ShopEntity getShopDetail(String name, String address){
        return shopRepository.findByNameAndAddress(name, address).orElseThrow(() -> new NotFoundException("해당 shopName과 address애 일치하는 ShopEntity가 없습니다. name: " + name + ", address: " + address));
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
    public ShopEntity findCategoryById(long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("해당 shopId에 일치하는 ShopEntity가 없습니다. id: " + shopId));
    }

    @Override
    public List<ShopEntity> findBookmarkDtosByIds(List<Long> shopIds){
        return shopRepository.findAllByIdIn(shopIds);
    }
}