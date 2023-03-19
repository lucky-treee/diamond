package com.luckytree.shop_service.shop.application.port.incoming;

public interface BookmarkUseCase {

    void createBookmark(long memberId, long shopId);
}