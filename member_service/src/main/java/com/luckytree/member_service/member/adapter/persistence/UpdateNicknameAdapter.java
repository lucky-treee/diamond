package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.member.application.port.outgoing.UpdateNicknamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UpdateNicknameAdapter implements UpdateNicknamePort {

    private final MemberRepository memberRepository;

    @Override
    public MemberEntity findByEmail(String email){
        return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 이메일이 존재하지 않습니다."));
    }
}