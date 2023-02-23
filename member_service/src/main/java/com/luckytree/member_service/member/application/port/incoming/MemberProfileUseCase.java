package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.domain.Photo;

public interface MemberProfileUseCase {

    void updateMemberRequest(String email, String nickname, Photo photo);
}
