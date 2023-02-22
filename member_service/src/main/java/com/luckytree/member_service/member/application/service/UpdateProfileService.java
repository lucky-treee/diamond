package com.luckytree.member_service.member.application.service;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.application.port.incoming.MemberProfileUseCase;
import com.luckytree.member_service.member.application.port.outgoing.UpdateProfilePort;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateProfileService implements MemberProfileUseCase {

    private final UpdateProfilePort updateProfilePort;

    @Transactional
    public void updateProfileRequest(String email, String nickname, Photo photo) {
        MemberEntity memberEntity = updateProfilePort.findByEmail(email);
        MemberProfile member = updateProfilePort.updateProfile(email, nickname, photo);
    }
}