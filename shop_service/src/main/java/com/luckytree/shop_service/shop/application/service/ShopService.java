package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.shop.adapter.data.*;
import com.luckytree.shop_service.shop.adapter.jpa.ShopEntity;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop_service.shop.application.port.outgoing.MemberFeignClientPort;
import com.luckytree.shop_service.shop.application.port.outgoing.ShopPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopService implements ShopUseCase {

    private final ShopPort shopPort;
    private final MemberFeignClientPort memberFeignClientPort;

    @Override
    public void createShop(CreateShopDto createShopDto) {
        shopPort.createShop(createShopDto.toDomain());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryDto> findShopsByCategory(Category category) {
        return shopPort.getShopSummaryByCategory(category).stream().map(ShopSummaryDto::new).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryDto> findShopsByLatAndLng(double maxLat, double minLat, double maxLng, double minLng) {
        return shopPort.getShopAll(maxLat, minLat, maxLng, minLng).stream().map(ShopSummaryDto::new).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryDto> findShopsByHashtag(Hashtag hashtag) {
        return shopPort.getShopSummaryByHashtag(hashtag).stream().map(ShopSummaryDto::new).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ShopDetailDto findShopByNameAndAddress(String name, String address) {
        ShopEntity shopEntity = shopPort.getShopDetail(name, address);
        return new ShopDetailDto(shopEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public ShopDetailDto findShopById(long shopId) {
        ShopEntity shopEntity = shopPort.getShopDetailById(shopId);
        return new ShopDetailDto(shopEntity);
    }

    @Override
    public void deleteShop(String name, String address, String comment) {
        ShopEntity shopEntity = shopPort.getShopEntity(name, address);
        shopPort.deleteShop(shopEntity, comment);
    }

    @Override
    public void createBookmark(long memberId, long shopId) {
        Category category = shopPort.findCategoryById(shopId);
        memberFeignClientPort.saveBookmark(memberId, shopId, category);
    }

    @Transactional(readOnly = true)
    @Override
    public MyBookmarksDto findMyBookmarksDtoByIds(List<Long> shopIds) {
        List<ShopEntity> shopEntities = shopPort.findBookmarkDtosByIds(shopIds);
        List<BookmarkDto> bookmarkDtos = shopEntities.stream().map(BookmarkDto::new).toList();
        return new MyBookmarksDto(bookmarkDtos);
    }

    @Override
    public void deleteBookmark(long memberId, long shopId){
        memberFeignClientPort.deleteBookmark(memberId, shopId);
    }
}