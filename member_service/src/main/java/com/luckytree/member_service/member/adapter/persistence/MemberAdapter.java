package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.member.domain.MemberProfile;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class MemberAdapter implements MemberPort {

    private final MemberRepository memberRepository;

    @Override
    public MemberProfile getMemberProfile(String nickname) {
        MemberEntity memberEntity = memberRepository.findByNickname(nickname).orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        return new MemberProfile(memberEntity);
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
}
