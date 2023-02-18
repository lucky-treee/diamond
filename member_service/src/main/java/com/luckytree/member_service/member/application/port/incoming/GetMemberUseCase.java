package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.domain.MemberProfile;

public interface GetMemberUseCase {

    MemberProfile getMemberProfile(String nickname, String email);
}
