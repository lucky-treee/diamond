package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberInfoUseCase;
import com.luckytree.member_service.member.application.port.outgoing.UpdateNicknamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateNicknameService implements MemberInfoUseCase {

    private final UpdateNicknamePort updateNicknamePort;

    @Transactional
    public void updateNicknameRequest(String email, String newNickname) {
        MemberEntity member = updateNicknamePort.findByEmail(email);
        member.updateNickname(email, newNickname);
    }
}