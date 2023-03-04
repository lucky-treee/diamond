package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;
import com.luckytree.member_service.member.domain.Status;

public interface MemberUseCase {

    MemberProfile getMemberProfile(String nickname);
    void updateMemberRequest(String email, String nickname, Photo photo);
    void withdrawalMemberRequest(String email, Status status);
}
