package com.luckytree.member.member.application.port.outgoing;

import com.luckytree.member.member.adapter.data.BookmarksResponse;
import com.luckytree.member.member.adapter.data.FindBookmarkedShops;


public interface ShopFeignClientPort {

    BookmarksResponse findBookmarksByIds(FindBookmarkedShops FindBookmarkedShops);
}
