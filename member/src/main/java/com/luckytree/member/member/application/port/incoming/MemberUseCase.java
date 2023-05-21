package com.luckytree.member.member.application.port.incoming;

import com.luckytree.member.member.adapter.data.MemberResponse;
import com.luckytree.member.member.adapter.data.UpdateMemberRequest;

public interface MemberUseCase {
  
    MemberResponse getMember(Long memberId);
    void update(UpdateMemberRequest updateMemberRequest, Long memberId);
    void leave(Long memberId);
}