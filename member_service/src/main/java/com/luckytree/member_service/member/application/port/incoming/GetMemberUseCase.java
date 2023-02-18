package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.domain.MemberInfo;

public interface GetMemberUseCase {

    MemberInfo getMemberInfo(String nickname, String email);
}
