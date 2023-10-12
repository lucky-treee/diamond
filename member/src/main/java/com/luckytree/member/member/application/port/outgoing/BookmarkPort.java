package com.luckytree.member.member.application.port.outgoing;

import com.luckytree.member.member.adapter.mysql.bookmark.BookmarkEntity;
import com.luckytree.member.member.domain.bookmark.Bookmark;

import java.util.List;

public interface BookmarkPort {

    void create(Bookmark bookmark);

    Bookmark findByMemberIdAndShopId(Long memberId, Long shopId);

    void delete(Bookmark bookmark);

    List<BookmarkEntity> findAllByMemberId(long memberId);
}
