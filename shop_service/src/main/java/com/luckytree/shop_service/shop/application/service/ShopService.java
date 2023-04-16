package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.common.enums.Hashtag;
import com.luckytree.shop_service.common.utils.S3UploadUtil;
import com.luckytree.shop_service.common.utils.TokenUtil;
import com.luckytree.shop_service.shop.adapter.data.*;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ShopEntity;
import com.luckytree.shop_service.shop.application.port.incoming.ShopUseCase;
import com.luckytree.shop_service.shop.application.port.outgoing.MemberFeignClientPort;
import com.luckytree.shop_service.shop.application.port.outgoing.ShopPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
    public List<ShopSummaryDto> findShopsByLatAndLng(ShopSearchDto shopSearchDto) {
        return shopPort.getShopAll(shopSearchDto.getMaxLat(), shopSearchDto.getMinLat(), shopSearchDto.getMaxLng(), shopSearchDto.getMinLng())
                .stream()
                .map(ShopSummaryDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopSummaryDto> findShopsByHashtag(Hashtag hashtag) {
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
        TokenUtil.validateAuthorization(authorization);

        ShopEntity shopEntity = shopPort.getShopEntity(shopDeleteDto.getName(), shopDeleteDto.getAddress());
        shopPort.deleteShop(shopEntity, shopDeleteDto.getComment());
    }

    @Override
    public void createBookmark(String authorization, long shopId) {
        Long memberId = TokenUtil.parseMemberId(authorization);

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
    public void deleteBookmark(String authorization, long shopId){
        Long memberId = TokenUtil.parseMemberId(authorization);

        memberFeignClientPort.deleteBookmark(memberId, shopId);
    }

    @Override
    public void createShopReview(String authorization, CreateReviewDto createReviewDto) {
        Long memberId = TokenUtil.parseMemberId(authorization);
        createReviewDto.setMemberId(memberId);
        ReviewEntity reviewEntity = shopPort.createShopReview(createReviewDto.toDomain());
        if(createReviewDto.getMultipartFile() != null){
            String photoUrl;
            try {
                photoUrl = S3UploadUtil.upload(createReviewDto.getMultipartFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            CreateReviewPhotoDto createReviewPhotoDto = new CreateReviewPhotoDto(reviewEntity.getId(), photoUrl);
            createShopReviewPhoto(createReviewPhotoDto);
        }
    }

    @Override
    public void createShopReviewPhoto(CreateReviewPhotoDto createReviewPhotoDto) {
        shopPort.createShopReviewPhoto(createReviewPhotoDto.toDomain());
    }
}