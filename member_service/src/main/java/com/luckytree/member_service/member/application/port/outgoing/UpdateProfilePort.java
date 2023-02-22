package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;

public interface UpdateProfilePort {

    MemberEntity findByEmail(String email);
    MemberProfile updateProfile(String email, String nickname, Photo photo);
}
