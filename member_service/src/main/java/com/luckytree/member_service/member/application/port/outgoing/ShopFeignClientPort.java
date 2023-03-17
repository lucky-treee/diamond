package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.data.MyBookmarksDto;

import java.util.List;

public interface ShopFeignClientPort {

    MyBookmarksDto findBookmarksByIds(List<Long> shopIds);
}