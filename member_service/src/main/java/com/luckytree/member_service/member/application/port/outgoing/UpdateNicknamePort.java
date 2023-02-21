package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.domain.MemberProfile;

public interface UpdateNicknamePort {

    public MemberProfile updateNickname(String email, String newNickname);
}
