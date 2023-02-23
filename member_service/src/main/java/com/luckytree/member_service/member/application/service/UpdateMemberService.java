package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberProfileUseCase;
import com.luckytree.member_service.member.application.port.outgoing.UpdateMemberPort;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMemberService implements MemberProfileUseCase {

    private final UpdateMemberPort updateMemberPort;

    @Transactional
    @Override
    public void updateMemberRequest(String email, String nickname, Photo photo) {
        MemberEntity memberEntity = updateMemberPort.findByEmail(email);
        MemberProfile member = updateMemberPort.updateMember(email, nickname, photo);
    }
}