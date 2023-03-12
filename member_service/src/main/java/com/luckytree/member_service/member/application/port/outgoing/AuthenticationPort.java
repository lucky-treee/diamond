package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.domain.Member;

public interface AuthenticationPort {

    long saveMember(Member member);

    long findMemberIdByEmail(String email);
}
