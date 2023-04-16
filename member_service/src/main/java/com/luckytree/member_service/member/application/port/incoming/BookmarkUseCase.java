package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.BookmarksResponse;
import com.luckytree.member_service.member.adapter.data.CreateBookmarkRequest;

public interface BookmarkUseCase {

    void create(CreateBookmarkRequest createBookmarkRequest);
    BookmarksResponse getBookmarks(String authorization);
    void delete(String authorization, long shopId);
    void delete(long memberId, long shopId);
}
