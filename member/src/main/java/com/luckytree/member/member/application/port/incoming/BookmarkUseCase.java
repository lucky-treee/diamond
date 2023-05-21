package com.luckytree.member.member.application.port.incoming;

import com.luckytree.member.member.adapter.data.BookmarksResponse;
import com.luckytree.member.member.adapter.data.CreateBookmarkRequest;

public interface BookmarkUseCase {

    void create(CreateBookmarkRequest createBookmarkRequest);
    BookmarksResponse getBookmarks(String authorization, Long memberId);
    void delete(String authorization, long shopId, Long memberId);
    void delete(long memberId, long shopId);
}
