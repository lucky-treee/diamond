package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.member.application.port.outgoing.UpdateNicknamePort;
import com.luckytree.member_service.member.domain.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

@RequiredArgsConstructor
@Repository
public class UpdateNicknameAdapter implements UpdateNicknamePort {

    private final MemberRepository memberRepository;

    @Override
    public MemberProfile updateNickname(String email, String newNickname){
        MemberEntity memberEntity = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("해당 이메일이 존재하지 않습니다."));
        memberEntity.updateNickname(newNickname);
        return new MemberProfile(memberEntity);
    }
}