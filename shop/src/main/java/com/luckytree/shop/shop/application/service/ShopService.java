package com.luckytree.shop.shop.application.service;

import com.luckytree.shop.shop.adapter.data.*;
import com.luckytree.shop.shop.adapter.jpa.ShopEntity;
import com.luckytree.shop.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop.shop.application.port.outgoing.ShopPort;
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
    public void createShop(CreateShopDto createShopDto) {
        shopPort.createShop(createShopDto.toDomain());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryDto> findShopsByCategory(ShopCategory category) {
        return shopPort.getShopSummaryByCategory(category).stream().map(ShopSummaryDto::new).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryDto> findShopsByLatAndLng(ShopSearchDto shopSearchDto) {
        return shopPort.getShopAll(shopSearchDto.getMaxLat(), shopSearchDto.getMinLat(), shopSearchDto.getMaxLng(), shopSearchDto.getMinLng())
                .stream()
                .map(ShopSummaryDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryDto> findShopsByHashtag(ShopHashtag hashtag) {
        return shopPort.getShopSummaryByHashtag(hashtag).stream().map(ShopSummaryDto::new).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ShopDetailDto findShopById(long shopId) {
        ShopEntity shopEntity = shopPort.getShopDetailById(shopId);
        return new ShopDetailDto(shopEntity);
    }

    @Override
    public void deleteShop(String authorization, ShopDeleteDto shopDeleteDto) {
        ShopEntity shopEntity = shopPort.getShopEntity(shopDeleteDto.getName(), shopDeleteDto.getAddress());
        shopPort.deleteShop(shopEntity, shopDeleteDto.getComment());
    }

    @Transactional(readOnly = true)
    @Override
    public MyBookmarksDto findMyBookmarksDtoByIds(List<Long> shopIds) {
        List<ShopEntity> shopEntities = shopPort.findBookmarkDtosByIds(shopIds);
        List<BookmarkDto> bookmarkDtos = shopEntities.stream().map(BookmarkDto::new).toList();
        return new MyBookmarksDto(bookmarkDtos);
    }
}