package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.application.port.incoming.GetMemberUseCase;
import com.luckytree.member_service.member.application.port.outgoing.GetMemberPort;
import com.luckytree.member_service.member.domain.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetMemberService implements GetMemberUseCase {

    private final GetMemberPort getMemberPort;

    @Transactional(readOnly = true)
    @Override
    public MemberInfo getMemberInfo(String nickname, String email) {
        return getMemberPort.getMemberInfo(nickname, email);
    }
}
