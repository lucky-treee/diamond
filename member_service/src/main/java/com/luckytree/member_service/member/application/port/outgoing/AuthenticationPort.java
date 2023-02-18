package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.domain.Member;

public interface AuthenticationPort {

    String saveMember(Member member);

    boolean existsByEmail(String email);
}
