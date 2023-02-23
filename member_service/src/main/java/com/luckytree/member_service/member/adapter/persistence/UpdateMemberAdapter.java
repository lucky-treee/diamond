package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.member.application.port.outgoing.UpdateMemberPort;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

@RequiredArgsConstructor
@Repository
public class UpdateMemberAdapter implements UpdateMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public MemberEntity findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("해당 이메일이 존재하지 않습니다."));
    }

    @Override
    public MemberProfile updateMember(String email, String nickname, Photo photo){
        MemberEntity memberEntity = findByEmail(email);
        if(photo != null){
            memberEntity.updatePhoto(photo);
        }
        if(nickname != null){
            memberEntity.updateNickname(nickname);
        }
        return new MemberProfile(memberEntity);
    }
}