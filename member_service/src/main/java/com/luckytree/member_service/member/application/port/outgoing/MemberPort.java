package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Status;

public interface MemberPort {

    MemberProfile getMemberProfile(String nickname);

    Member findByEmail(String email);

    void updateMember(Member member);

    void updateMemberStatus(Member member, Status status);
}
