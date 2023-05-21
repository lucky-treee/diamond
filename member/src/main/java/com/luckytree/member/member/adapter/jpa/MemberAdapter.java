package com.luckytree.member.member.adapter.jpa;

import com.luckytree.member.member.application.port.outgoing.MemberPort;
import com.luckytree.member.member.domain.member.Member;
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
    public Member save(Member member) {
        return memberRepository.save(new MemberEntity(member)).toDomain();
    }

    @Override
    public Member findById(long id) {
        return memberRepository.findById(id).orElseThrow(NotFoundException::new).toDomain();
    }

    @Override
    public Member findByIdAndEmail(Member member) {
        return memberRepository.findByIdAndEmail(member.getId(), member.getEmail()).orElseThrow(NotFoundException::new).toDomain();
    }
}
