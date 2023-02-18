package com.luckytree.member_service.member.application.port.incoming;

public interface MemberInfoUseCase {

    void updateNicknameRequest(String email, String newNickname);
}
