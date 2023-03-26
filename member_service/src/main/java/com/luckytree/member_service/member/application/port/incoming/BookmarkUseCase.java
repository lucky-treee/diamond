package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member_service.member.adapter.data.BookmarksResponse;

public interface BookmarkUseCase {

    void create(CreateBookmarkRequest createBookmarkRequest);
    BookmarksResponse getBookmarks(long memberId);
    void delete(long memberId, long shopId);
}
