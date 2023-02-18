package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.member.application.port.outgoing.GetMemberPort;
import com.luckytree.member_service.member.domain.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

@RequiredArgsConstructor
@Repository
public class GetMemberPersistenceAdapter implements GetMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public MemberInfo getMemberInfo(String nickname, String email) {
        MemberEntity memberEntity = memberRepository.findByNicknameAndEmail(nickname, email).orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        return new MemberInfo(memberEntity);
    } 
}
