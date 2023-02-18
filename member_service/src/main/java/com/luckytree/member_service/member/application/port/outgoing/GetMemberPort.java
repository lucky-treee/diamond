package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.domain.MemberInfo;

public interface GetMemberPort {

    MemberInfo getMemberInfo(String nickname, String  email);
}
