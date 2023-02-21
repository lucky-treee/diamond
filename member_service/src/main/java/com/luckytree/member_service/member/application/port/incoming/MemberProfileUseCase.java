package com.luckytree.member_service.member.application.port.incoming;

public interface MemberProfileUseCase {

    void updateNicknameRequest(String email, String newNickname);
}
