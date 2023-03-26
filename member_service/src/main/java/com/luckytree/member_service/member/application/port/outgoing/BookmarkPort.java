package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member_service.member.adapter.persistence.BookmarkEntity;

import java.util.List;

public interface BookmarkPort {

    void create(CreateBookmarkRequest createBookmarkRequest);
    void deleteByMemberIdAndShopId(long memberId, long shopId);
    List<BookmarkEntity> findAllByMemberId(long memberId);
}
