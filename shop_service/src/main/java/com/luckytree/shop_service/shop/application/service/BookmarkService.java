package com.luckytree.shop_service.shop.application.service;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.data.MemberFeignRequestDto;
import com.luckytree.shop_service.shop.application.port.incoming.BookmarkUseCase;
import com.luckytree.shop_service.shop.application.port.outgoing.MemberFeignClientPort;
import com.luckytree.shop_service.shop.application.port.outgoing.ShopPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService implements BookmarkUseCase {

    private final ShopPort shopPort;
    private final MemberFeignClientPort memberFeignClientPort;

    @Override
    public void createBookmark(long memberId, long shopId) {
        Category category = shopPort.findCategoryById(shopId).getCategory();
        memberFeignClientPort.saveBookmark(new MemberFeignRequestDto(memberId, shopId, category));
    }
}