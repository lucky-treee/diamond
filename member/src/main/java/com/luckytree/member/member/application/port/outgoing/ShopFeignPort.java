package com.luckytree.member.member.application.port.outgoing;

import com.luckytree.member.member.adapter.data.bookmark.BookmarksResponse;
import com.luckytree.member.member.adapter.data.bookmark.FindBookmarkedShops;


public interface ShopFeignPort {

    BookmarksResponse findBookmarksByIds(FindBookmarkedShops FindBookmarkedShops);
}
