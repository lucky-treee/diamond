package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.domain.MemberProfile;

public interface MemberPort {

    MemberProfile getMemberProfile(String nickname);

    Member findByEmail(String email);

    Member findById(long memberId);

    void updateMember(Member member);

    void updateMemberStatus(Member member);
}
