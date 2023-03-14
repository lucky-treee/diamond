package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.shop.adapter.data.BookmarkDto;
import com.luckytree.shop_service.shop.adapter.data.Category;
import com.luckytree.shop_service.shop.adapter.data.MyBookmarksDto;
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
    public void createShop(ShopRequest shopRequest) {
        shopPort.saveShopWithDisable(shopRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummary> findShopsByCategory(Category category) {
        return shopPort.getShopSummaryByCategory(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummary> findShopsByLatAndLng(double maxLat, double minLat, double maxLng, double minLng) {
        return shopPort.getShopAll(maxLat, minLat, maxLng, minLng);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummary> findShopsByHashtag(Hashtag hashtag) {
        return shopPort.getShopSummaryByHashtag(hashtag);
    }

    @Transactional(readOnly = true)
    @Override
    public ShopDetail findShopByNameAndAddress(String name, String address) {
        return shopPort.getShopDetail(name, address);
    }

    @Transactional
    @Override
    public void deleteShop(RemoveRequestForm removeRequestForm) {
        ShopEntity shopEntity = shopPort.getShopEntity(removeRequestForm.getName(), removeRequestForm.getAddress());
        shopPort.saveRemoveRequest(shopEntity, removeRequestForm.getComment());
    }

    @Override
    public MyBookmarksDto findMyBookmarksDtoByIds(List<Long> shopIds) {
        List<BookmarkDto> bookmarkDtos = shopPort.findBookmarkDtosByIds(shopIds);
        return new MyBookmarksDto(bookmarkDtos);
    }
}
