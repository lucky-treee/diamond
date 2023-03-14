package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.domain.Member;

public interface MemberPort {

    Member findMemberById(long memberId);
    Member findByEmail(String email);
    void updateMember(Member member);
    void deleteMemberById(long memberId);
}
