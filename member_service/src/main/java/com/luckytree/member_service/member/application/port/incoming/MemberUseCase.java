package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.MemberProfile;

public interface MemberUseCase {

    MemberProfile getMemberProfile(long memberId);
    void updateMember(String email, String nickname, String photo);
    void leaveMember(long memberId);
}