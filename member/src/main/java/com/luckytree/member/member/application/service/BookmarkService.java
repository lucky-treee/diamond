package com.luckytree.member.member.application.service;

import com.luckytree.member.member.adapter.data.bookmark.BookmarksResponse;
import com.luckytree.member.member.adapter.data.bookmark.FindBookmarkedShops;
import com.luckytree.member.member.adapter.jpa.BookmarkEntity;
import com.luckytree.member.member.application.port.incoming.BookmarkUseCase;
import com.luckytree.member.member.application.port.outgoing.BookmarkPort;
import com.luckytree.member.member.application.port.outgoing.ShopFeignClientPort;
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
    private final ShopFeignClientPort shopFeignClientPort;

    @Override
    @Transactional
    public void create(Bookmark bookmark) {
        bookmarkPort.create(bookmark);
    }

    @Override
    @Transactional
    public void delete(Long shopId) {
        bookmarkPort.delete(bookmarkPort.findByMemberIdAndShopId((Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), shopId));
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
        return shopFeignClientPort.findBookmarksByIds(new FindBookmarkedShops(shopIds));
    }
}
