package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.domain.MemberProfile;

public interface GetMemberPort {

    MemberProfile getMemberProfile(String nickname);
}
