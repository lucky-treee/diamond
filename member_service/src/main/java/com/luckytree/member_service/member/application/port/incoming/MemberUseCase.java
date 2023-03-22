package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.MyBookmarksDto;
import com.luckytree.member_service.member.domain.MemberProfile;

public interface MemberUseCase {

    MemberProfile getMemberProfile(long memberId);
    void updateMember(String email, String nickname, String photo);
    void leaveMember(long memberId);
    MyBookmarksDto findMyBookmarks(long memberId);
    void deleteBookMark(long memberId, String shopId);
}
