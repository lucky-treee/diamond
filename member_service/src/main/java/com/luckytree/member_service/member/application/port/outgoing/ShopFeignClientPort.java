package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.data.BookmarksResponse;

import java.util.List;

public interface ShopFeignClientPort {

    BookmarksResponse findBookmarksByIds(List<Long> shopIds);
}
