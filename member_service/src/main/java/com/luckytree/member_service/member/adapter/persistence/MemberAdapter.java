package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.common.advice.NotFoundException;
import com.luckytree.member_service.member.application.port.outgoing.MemberPort;
import com.luckytree.member_service.member.domain.Bookmark;
import com.luckytree.member_service.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
