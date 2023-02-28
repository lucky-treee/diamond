package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import com.luckytree.member_service.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AuthenticationAdapter implements AuthenticationPort {

    private final MemberRepository memberRepository;

    @Override
    public String saveMember(Member member) {
        return memberRepository.save(member.toEntity()).getEmail();
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
