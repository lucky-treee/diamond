package com.luckytree.shop.shop.application.service;

import com.luckytree.shop.shop.adapter.data.bookmark.BookmarkResponse;
import com.luckytree.shop.shop.adapter.data.bookmark.MyBookmarksResponse;
import com.luckytree.shop.shop.adapter.data.shop.*;
import com.luckytree.shop.shop.adapter.jpa.shop.ShopEntity;
import com.luckytree.shop.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop.shop.application.port.outgoing.ShopPort;
import com.luckytree.shop.shop.domain.shop.DeleteShop;
import com.luckytree.shop.shop.domain.shop.Shop;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopService implements ShopUseCase {

    private final ShopPort shopPort;

    @Override
    public void createShop(Shop shop) {
        if(shop.valid(shop).equals("success")) shopPort.createShop(shop);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryResponse> findShopsByCategory(ShopCategory category) {
        return shopPort.getShopSummaryByCategory(category).stream().map(ShopSummaryResponse::new).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryResponse> findShopsByLatAndLng(SearchShopRequest searchShopRequest) {
        return shopPort.getShopAll(searchShopRequest.getMaxLat(), searchShopRequest.getMinLat(), searchShopRequest.getMaxLng(), searchShopRequest.getMinLng())
                .stream()
                .map(ShopSummaryResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryResponse> findShopsByHashtag(ShopHashtag hashtag) {
        return shopPort.getShopSummaryByHashtag(hashtag).stream().map(ShopSummaryResponse::new).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ShopDetailResponse findShopById(Long shopId) {
        ShopEntity shopEntity = shopPort.getShopDetailById(shopId);
        return new ShopDetailResponse(shopEntity);
    }

    @Override
    public void deleteShop(DeleteShop deleteShop) {
        ShopEntity shopEntity = shopPort.getShopEntity(deleteShop.getName(), deleteShop.getAddress());
        shopPort.deleteShop(shopEntity, deleteShop.getComment());
    }

    @Transactional(readOnly = true)
    @Override
    public MyBookmarksResponse findMyBookmarksResponseByIds(List<Long> shopIds) {
        return new MyBookmarksResponse(shopPort.findBookmarksByIds(shopIds).stream().
                map(BookmarkResponse::new).toList());
    }
}