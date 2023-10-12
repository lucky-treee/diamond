package com.luckytree.member.member.application.port.incoming;

import com.luckytree.member.member.adapter.data.member.GetMemberResponse;
import com.luckytree.member.member.adapter.data.member.MemberResponse;
import com.luckytree.member.member.domain.member.Member;
import com.luckytree.member.member.domain.member.MemberDetail;

public interface MemberUseCase {
  
    MemberResponse getMember(Long id);

    GetMemberResponse getMember(Member member);

    void update(MemberDetail memberDetail);

    void leave(Long memberId);

    MemberResponse create(Member member);
}