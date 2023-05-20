package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.MemberResponse;
import com.luckytree.member_service.member.adapter.data.UpdateMemberRequest;

public interface MemberUseCase {

    MemberResponse getMember(String authorization);

    void update(String authorization, UpdateMemberRequest updateMemberRequest);

    void leave(String authorization);
}