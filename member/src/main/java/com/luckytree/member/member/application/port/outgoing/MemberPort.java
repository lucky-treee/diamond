package com.luckytree.member.member.application.port.outgoing;

import com.luckytree.member.member.adapter.jpa.MemberEntity;

public interface MemberPort {

    MemberEntity findById(long memberId);
    void deleteById(MemberEntity memberEntity);
}
