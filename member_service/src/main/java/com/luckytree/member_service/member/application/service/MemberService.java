package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.jpa.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.adapter.data.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;

    @Transactional(readOnly = true)
    @Override
    public MemberResponse getMember(long memberId) {
        MemberEntity memberEntity = memberPort.findById(memberId);
        return new MemberResponse(memberEntity);
    }

    @Transactional
    @Override
    public void update(long memberId, String nickname, String photo) {
        MemberEntity memberEntity = memberPort.findById(memberId);
        memberEntity.update(nickname, photo);
    }

    @Transactional
    @Override
    public void leave(long memberId) {
        MemberEntity memberEntity = memberPort.findById(memberId);
        memberEntity.isAlreadyDeleted();
        memberPort.deleteById(memberEntity);
    }
}