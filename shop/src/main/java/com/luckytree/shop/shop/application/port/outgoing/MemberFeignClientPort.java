package com.luckytree.shop.shop.application.port.outgoing;

import com.luckytree.shop.common.enums.Category;

public interface MemberFeignClientPort {

    void saveBookmark(long memberId, long shopId, Category category);
    void deleteBookmark(long memberId, long shopId);
}