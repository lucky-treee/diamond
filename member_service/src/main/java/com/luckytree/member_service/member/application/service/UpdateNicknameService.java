package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberProfileUseCase;
import com.luckytree.member_service.member.application.port.outgoing.UpdateNicknamePort;
import com.luckytree.member_service.member.domain.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateNicknameService implements MemberProfileUseCase {

    private final UpdateNicknamePort updateNicknamePort;

    @Transactional
    public void updateNicknameRequest(String email, String newNickname) {
        MemberProfile member = updateNicknamePort.updateNickname(email, newNickname);
    }
}