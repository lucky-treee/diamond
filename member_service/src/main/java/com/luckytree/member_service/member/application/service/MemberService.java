package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;
import com.luckytree.member_service.member.domain.Status;
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

    @Transactional
    @Override
    public void deleteMemberRequest(long memberId) {
        Member member = getMember(memberId);  // id를 통해서 가져온 회원 객체
        Status status = member.getStatus();   // 가져온 회원 status값을 변수에담고
        member.updateStatus(status.isMemberStatus(status));
        memberPort.updateMemberStatus(member);
    }

/*
    @Override
    public List<ShopDetailDto> getBookMark(long memberId) {
        return null;
    }

    @Transactional
    @Override
    public void deleteBookMark(long memberId, String shopId) {

    }
*/

    private Member getMember(String email) {
        return memberPort.findByEmail(email);
    }

    private Member getMember(long memberId) {
        return memberPort.findById(memberId);
    }
}
