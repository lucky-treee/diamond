package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.data.CreateBookmarkRequest;
import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.outgoing.BookmarkPort;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.application.port.outgoing.ShopFeignClientPort;
import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.adapter.data.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;

    @Transactional(readOnly = true)
    @Override
    public MemberProfile getMemberProfile(long memberId) {
        Member member = memberPort.findMemberById(memberId);
        return new MemberProfile(member);
    }

    @Transactional
    @Override
    public void updateMember(String email, String nickname, String photo) {
        Member member = getMember(email);
        member.updateNicknameAndPhoto(nickname, photo);
        memberPort.updateMember(member);
    }

    @Transactional
    @Override
    public void leaveMember(long memberId) {
        MemberEntity memberEntity = memberPort.findById(memberId);
        memberEntity.isAlreadyDeleted();
        memberPort.deleteById(memberEntity);
    }

    private Member getMember(String email) {
        return memberPort.findByEmail(email);
    }
}