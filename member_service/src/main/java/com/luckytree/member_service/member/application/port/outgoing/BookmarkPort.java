package com.luckytree.member_service.member.application.port.outgoing;

public interface BookmarkPort {

    void deleteBookmarkByMemberIdAndShopId(long memberId, long shopId);
}
