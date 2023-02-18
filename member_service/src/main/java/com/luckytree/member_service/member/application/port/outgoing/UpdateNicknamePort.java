package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;

public interface UpdateNicknamePort {

    public MemberEntity findByEmail(String email);
}
