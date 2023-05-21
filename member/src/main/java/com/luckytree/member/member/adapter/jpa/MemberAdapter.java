package com.luckytree.member.member.adapter.jpa;

import com.luckytree.member.member.application.port.outgoing.MemberPort;
import lombok.RequiredArgsConstructor;
import luckytree.poom.core.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class MemberAdapter implements MemberPort {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void deleteById(MemberEntity memberEntity) {
        memberEntity.leave();
    }

    @Override
    public MemberEntity findById(long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
    }
}
