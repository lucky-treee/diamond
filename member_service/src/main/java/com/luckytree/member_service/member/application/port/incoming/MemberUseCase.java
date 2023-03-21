package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.MemberFeignResponseDto;
import com.luckytree.member_service.member.adapter.data.MyBookmarksDto;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;

public interface MemberUseCase {

    MemberProfile getMemberProfile(long memberId);
    void updateMember(String email, String nickname, Photo photo);
    void leaveMember(long memberId);
    void createBookmark(MemberFeignResponseDto memberFeignResponseDto);
    MyBookmarksDto findMyBookmarks(long memberId);
    void deleteBookMark(long memberId, String shopId);
}