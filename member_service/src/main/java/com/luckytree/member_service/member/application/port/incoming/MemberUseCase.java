package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.MemberResponse;
import com.luckytree.member_service.member.adapter.data.UpdateMemberRequest;

public interface MemberUseCase {
  
    MemberResponse getMember(String authorization, Long memberId);
    void update(String authorization, UpdateMemberRequest updateMemberRequest, Long memberId);
    void leave(String authorization, Long memberId);
}