package com.luckytree.member.member.application.port.incoming;

import com.luckytree.member.member.adapter.data.bookmark.BookmarksResponse;
import com.luckytree.member.member.domain.bookmark.Bookmark;

public interface BookmarkUseCase {

    void create(Bookmark bookmark);

    void delete(Long memberId, Long shopId);

    BookmarksResponse getBookmarks(Long memberId);
}
