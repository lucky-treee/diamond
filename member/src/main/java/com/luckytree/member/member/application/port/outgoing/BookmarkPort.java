package com.luckytree.member.member.application.port.outgoing;

import com.luckytree.member.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member.member.adapter.jpa.BookmarkEntity;

import java.util.List;

public interface BookmarkPort {

    void create(CreateBookmarkRequest createBookmarkRequest);
    void deleteByMemberIdAndShopId(long memberId, long shopId);
    List<BookmarkEntity> findAllByMemberId(long memberId);
}
