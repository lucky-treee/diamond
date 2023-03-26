package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.MemberResponse;

public interface MemberUseCase {

    MemberResponse getMember(long memberId);
    void update(long memberId, String nickname, String photo);
    void leave(long memberId);
}