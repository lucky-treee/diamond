package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.ShopDetailDto;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;

import java.util.List;

public interface MemberUseCase {

    MemberProfile getMemberProfile(long memberId);
    void updateMember(String email, String nickname, Photo photo);
    void deleteMember(long userId);
    List<ShopDetailDto> getBookMark(long memberId);
    void deleteBookMark(long memberId, String shopId);
}
