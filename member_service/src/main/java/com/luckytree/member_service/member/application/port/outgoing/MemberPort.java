package com.luckytree.member_service.member.application.port.outgoing;

import com.luckytree.member_service.member.adapter.data.ReviewResponse;
import com.luckytree.member_service.member.adapter.jpa.MemberEntity;

public interface MemberPort {

    MemberEntity findById(long memberId);
    void deleteById(MemberEntity memberEntity);
}
