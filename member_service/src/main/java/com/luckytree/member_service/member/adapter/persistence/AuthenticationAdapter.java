package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.common.advice.NotFoundException;
import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import com.luckytree.member_service.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AuthenticationAdapter implements AuthenticationPort {

    private final MemberRepository memberRepository;

    @Override
    public long saveMember(Member member) {
        return memberRepository.save(member.toEntity()).getId();
    }

    @Override
    public long findMemberIdByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("해당 이메일에 일치하는 회원이 없습니다.")).getId();
    }
}
