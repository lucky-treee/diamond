package com.luckytree.shop.shop.application.port.outgoing;

import luckytree.poom.core.enums.ShopCategory;

public interface MemberFeignClientPort {

    void saveBookmark(long memberId, long shopId, ShopCategory category);
    void deleteBookmark(long memberId, long shopId);
}