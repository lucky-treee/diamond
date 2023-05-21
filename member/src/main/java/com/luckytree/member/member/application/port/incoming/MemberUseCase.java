package com.luckytree.member.member.application.port.incoming;

import com.luckytree.member.member.adapter.data.MemberResponse;
import com.luckytree.member.member.adapter.data.UpdateMemberRequest;

public interface MemberUseCase {
  
    MemberResponse getMember(String authorization, Long memberId);
    void update(String authorization, UpdateMemberRequest updateMemberRequest, Long memberId);
    void leave(String authorization, Long memberId);
}