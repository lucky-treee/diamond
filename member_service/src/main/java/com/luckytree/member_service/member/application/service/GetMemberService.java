package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.application.port.incoming.GetMemberUseCase;
import com.luckytree.member_service.member.application.port.outgoing.GetMemberPort;
import com.luckytree.member_service.member.domain.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetMemberService implements GetMemberUseCase {

    private final GetMemberPort getMemberPort;

    @Transactional(readOnly = true)
    @Override
    public MemberProfile getMemberProfile(String nickname) {
        return getMemberPort.getMemberProfile(nickname);
    }
}
