package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.common.advice.NotFoundException;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.domain.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class MemberAdapter implements MemberPort {

    private final MemberRepository memberRepository;

    @Override
    public Member findMemberById(long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        return memberEntity.toDomain();
    }

    @Override
    public Member findByEmail(String email) {
        MemberEntity memberEntity = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("해당 이메일이 존재하지 않습니다."));
        return new Member(memberEntity);
    }

    @Override
    @Transactional
    public void updateMember(Member member){
        MemberEntity memberEntity = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new NotFoundException("해당 이메일이 존재하지 않습니다."));
        memberEntity.updateNicknameAndPhoto(member.getNickname(), member.getPhoto());
    }

    @Transactional
    @Override
    public void updateMemberStatus(Member member) {
        MemberEntity memberEntity = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new NotFoundException("해당 이메일이 존재하지 않습니다."));
        memberEntity.updateMemberStatus(member.getStatus());
    }

    @Transactional(readOnly = true)
    @Override
    public Member findById(long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("해당하는 회원ID가 존재하지 않습니다."));
        return new Member(memberEntity);
    }
}
