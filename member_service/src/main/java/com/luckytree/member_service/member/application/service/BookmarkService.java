package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.common.utils.TokenUtil;
import com.luckytree.member_service.member.adapter.data.BookmarksResponse;
import com.luckytree.member_service.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member_service.member.adapter.data.FindBookmarkedShops;
import com.luckytree.member_service.member.adapter.jpa.BookmarkEntity;
import com.luckytree.member_service.member.application.port.incoming.BookmarkUseCase;
import com.luckytree.member_service.member.application.port.outgoing.BookmarkPort;
import com.luckytree.member_service.member.application.port.outgoing.ShopFeignClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookmarkService implements BookmarkUseCase {

    private final BookmarkPort bookmarkPort;
    private final ShopFeignClientPort shopFeignClientPort;

    @Override
    @Transactional
    public void create(CreateBookmarkRequest createBookmarkRequest) {
        bookmarkPort.create(createBookmarkRequest);
    }

    @Override
    public BookmarksResponse getBookmarks(String authorization) {
        Long memberId = TokenUtil.parseMemberId(authorization);
        List<Long> shopIds = findShopIds(memberId);
        return findBookmarkedShops(shopIds);
    }

    @Override
    @Transactional
    public void delete(String authorization, long shopId) {
        Long memberId = TokenUtil.parseMemberId(authorization);
        bookmarkPort.deleteByMemberIdAndShopId(memberId, shopId);
    }

    @Override
    public void delete(long memberId, long shopId) {
        bookmarkPort.deleteByMemberIdAndShopId(memberId, shopId);
    }

    private List<Long> findShopIds(long memberId) {
        List<BookmarkEntity> bookmarkEntities = bookmarkPort.findAllByMemberId(memberId);
        return bookmarkEntities.stream().map(BookmarkEntity::getShopId).toList();
    }

    private BookmarksResponse findBookmarkedShops(List<Long> shopIds) {
        return shopFeignClientPort.findBookmarksByIds(new FindBookmarkedShops(shopIds));
    }
}
