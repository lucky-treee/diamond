package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.domain.MemberProfile;
import com.luckytree.member_service.member.domain.Photo;

public interface UpdateMemberPort {

    MemberEntity findByEmail(String email);
    MemberProfile updateMember(String email, String nickname, Photo photo);
}
