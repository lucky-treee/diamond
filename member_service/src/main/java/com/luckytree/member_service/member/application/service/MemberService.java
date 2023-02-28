package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;

    @Transactional(readOnly = true)
    @Override
    public MemberProfile getMemberProfile(String nickname) {
        return memberPort.getMemberProfile(nickname);
    }

    @Transactional
    @Override
    public void updateMemberRequest(String email, String nickname, Photo photo) {
        Member member = getMember(email);
        member.updateNicknameAndPhoto(nickname, photo);
        memberPort.updateMember(member);
    }

    private Member getMember(String email) {
        return memberPort.findByEmail(email);
    }
}
