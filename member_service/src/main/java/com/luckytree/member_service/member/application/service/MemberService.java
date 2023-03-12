package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.common.advice.NotFoundException;
import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberUseCase;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;
import com.luckytree.member_service.member.domain.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.luckytree.member_service.member.domain.Status.LEAVE;

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
    public void deleteMemberRequest(String email) {
        Member member = getMember(email);
        Status status = validMemberStatus(member);
        member.updateStatus(status);
        memberPort.updateMemberStatus(member,status);
    }

    private Status validMemberStatus(Member member) {
        if (member.getStatus() != LEAVE)
            return LEAVE;
        else
            throw new NotFoundException("이미 탈퇴한 회원입니다.");
    }

    @Override
    public List<ShopDetailDto> getBookMark(long memberId) {
        return null;
    }

    @Transactional
    @Override
    public void deleteBookMark(long memberId, String shopId) {

    }

    private Member getMember(String email) {
        return memberPort.findByEmail(email);
    }
}
