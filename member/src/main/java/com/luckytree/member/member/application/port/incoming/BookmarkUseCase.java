package com.luckytree.member.member.application.port.incoming;

import com.luckytree.member.member.adapter.data.BookmarksResponse;
import com.luckytree.member.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member.member.domain.bookmark.Bookmark;

public interface BookmarkUseCase {

    void create(Bookmark bookmark);

    void delete(Long shopId);

    BookmarksResponse getBookmarks(Long memberId);
}
