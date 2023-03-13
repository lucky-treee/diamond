package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.data.ShopDetailDto;
import com.luckytree.member_service.member.domain.Member;

import java.util.List;

public interface MemberPort {

    Member findMemberById(long memberId);
    Member findByEmail(String email);
    void updateMember(Member member);
    List<Long> getBookmarkIds(long memberId);
    List<ShopDetailDto> getBookmark(List bookmarkIds);
}
