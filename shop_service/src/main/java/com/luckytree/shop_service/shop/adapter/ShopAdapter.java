package com.luckytree.shop_service.shop.adapter;

import com.luckytree.shop_service.shop.adapter.data.BookmarkDto;
import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.data.CreateShopDto;
import com.luckytree.shop_service.shop.adapter.jpa.ShopEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ShopRemoveEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ShopRemoveRepository;
import com.luckytree.shop_service.shop.adapter.jpa.ShopRepository;
import com.luckytree.shop_service.shop.application.port.outgoing.ShopPort;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.shop.adapter.data.ShopDetail;
import com.luckytree.shop_service.common.enums.ShopStatus;
import com.luckytree.shop_service.shop.adapter.data.ShopSummary;
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
    public List<ShopSummary> getShopSummaryByCategory(Category category) {
        return shopRepository.findByCategoryAndStatus(category, ShopStatus.ENABLE).stream().map(ShopSummary::new).toList();
    }

    @Override
    public List<ShopSummary> getShopAll(double maxLat, double minLat, double maxLng, double minLng) {
        return shopRepository.findByStatusAndLatLessThanEqualAndLatGreaterThanEqualAndLngLessThanEqualAndLngGreaterThanEqual(ShopStatus.ENABLE, maxLat, minLat, maxLng, minLng).stream().map(ShopSummary::new).toList();
    }

    @Override
    public ShopDetail getShopDetail(String name, String address){
        ShopEntity shopEntity = shopRepository.findByNameAndAddress(name, address).orElseThrow(() -> new NotFoundException("해당 shopName과 address애 일치하는 ShopEntity가 없습니다. name: " + name + ", address: " + address));
        return new ShopDetail(shopEntity);
    }

    @Override
    public List<ShopSummary> getShopSummaryByHashtag(Hashtag hashtag) {
        return shopRepository.findByHashtagAndStatus(hashtag, ShopStatus.ENABLE).stream().map(ShopSummary::new).toList();
    }

    @Override
    public void saveRemoveRequest(ShopEntity shopEntity, String comment) {
        shopRemoveRepository.save(new ShopRemoveEntity(shopEntity, comment));
    }

    @Override
    public ShopEntity getShopEntity(String name, String address) {
        return shopRepository.findByNameAndAddress(name, address).orElseThrow(() -> new NotFoundException("해당 shopName과 address애 일치하는 ShopEntity가 없습니다. name: " + name + ", address: " + address));
    }

    @Override
    public List<BookmarkDto> findBookmarkDtosByIds(List<Long> shopIds){
        return shopRepository.findAllByIdIn(shopIds).stream().map(BookmarkDto::new).toList();
    }
}
