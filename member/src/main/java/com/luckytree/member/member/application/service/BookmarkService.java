package com.luckytree.member.member.application.service;

import com.luckytree.member.member.adapter.data.bookmark.BookmarksResponse;
import com.luckytree.member.member.adapter.data.bookmark.FindBookmarkedShops;
import com.luckytree.member.member.adapter.mysql.bookmark.BookmarkEntity;
import com.luckytree.member.member.application.port.incoming.BookmarkUseCase;
import com.luckytree.member.member.application.port.outgoing.BookmarkPort;
import com.luckytree.member.member.application.port.outgoing.ShopFeignPort;
import com.luckytree.member.member.domain.bookmark.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookmarkService implements BookmarkUseCase {

    private final BookmarkPort bookmarkPort;
    private final ShopFeignPort shopFeignPort;

    @Override
    @Transactional
    public void create(Bookmark bookmark) {
        bookmarkPort.create(bookmark);
    }

    @Override
    @Transactional
    public void delete(Long memberId, Long shopId) {
        bookmarkPort.delete(bookmarkPort.findByMemberIdAndShopId(memberId, shopId));
    }

    @Override
    public BookmarksResponse getBookmarks(Long memberId) {
        List<Long> shopIds = findShopIds(memberId);
        return findBookmarkedShops(shopIds);
    }

    private List<Long> findShopIds(long memberId) {
        List<BookmarkEntity> bookmarkEntities = bookmarkPort.findAllByMemberId(memberId);
        return bookmarkEntities.stream().map(BookmarkEntity::getShopId).toList();
    }

    private BookmarksResponse findBookmarkedShops(List<Long> shopIds) {
        return shopFeignPort.findBookmarksByIds(new FindBookmarkedShops(shopIds));
    }
}
