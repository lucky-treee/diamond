package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.common.enums.Category;

public interface MemberFeignClientPort {

    void saveBookmark(long memberId, long shopId, Category category);
}